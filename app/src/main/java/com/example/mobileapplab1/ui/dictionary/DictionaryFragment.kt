package com.example.mobileapplab1.ui.dictionary

import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileapplab1.*
import com.example.mobileapplab1.databinding.FragmentDictionaryBinding
import com.squareup.moshi.Moshi
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

class DictionaryFragment : Fragment() {

    private var _binding: FragmentDictionaryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var mediaPlayer: MediaPlayer

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDictionaryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val moshi = Moshi.Builder()
            .add(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.dictionaryapi.dev/api/v2/entries/en/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val service = retrofit.create(DictionaryApi::class.java)
        val meaningItems: MutableList<DictionaryMeaningItem> = mutableListOf()
        val dictionaryMeaningItemsAdapter = DictionaryMeaningItemsAdapter(meaningItems)

        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        var playerAvailable = false;

        binding.textInputLayoutCookingSearch.setEndIconOnClickListener {
            val word: String = binding.dictionaryInputCooking.text.toString()
            playerAvailable = false;
            lifecycleScope.launch(Dispatchers.IO) {
                val result: WordInformation;
                try {
                    result = service.getWordMeaning(word)[0]
                } catch (e: retrofit2.HttpException) {
                    e.printStackTrace()
                    withContext(Dispatchers.Main) {
                        AlertDialog.Builder(binding.root.context)
                            .setTitle(getString(R.string.dictionary_not_found_title))
                            .setMessage(R.string.dictionary_not_found_desc)
                            .show()
                    }
                    return@launch;
                }
                withContext(Dispatchers.Main) {

                    if (result.phonetics[0].audio != "") {
                        mediaPlayer.setDataSource(result.phonetics[0].audio)
                        mediaPlayer.prepareAsync()
                        playerAvailable = true;
                    }
                    binding.textWord.text = result.word
                    binding.textPhonetic.text = result.phonetics[0].transcription
                    binding.textPartOfSpeech.text = result.meanings[0].partOfSpeech
                    meaningItems.clear()
                    for (definition in result.meanings[0].definitions) {
                        meaningItems += DictionaryMeaningItem(definition.definition, definition.example)
                    }
                    dictionaryMeaningItemsAdapter.notifyDataSetChanged()


                }
            }
        }

        binding.imagePlaySound.setOnClickListener {
            if (playerAvailable) {
                mediaPlayer.start()
            }
        }

        binding.recyclerViewDictionary.layoutManager = LinearLayoutManager(root.context)
        binding.recyclerViewDictionary.adapter = dictionaryMeaningItemsAdapter



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}