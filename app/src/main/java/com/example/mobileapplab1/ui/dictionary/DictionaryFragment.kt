package com.example.mobileapplab1.ui.dictionary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobileapplab1.*
import com.example.mobileapplab1.databinding.FragmentDictionaryBinding

class DictionaryFragment : Fragment() {

    private var _binding: FragmentDictionaryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dictionaryViewModel =
            ViewModelProvider(this).get(DictionaryViewModel::class.java)

        _binding = FragmentDictionaryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val dictionaryMeaningItemsAdapter = DictionaryMeaningItemsAdapter(
            listOf(
                DictionaryMeaningItem(
                    meaning = "The practice or skill of preparing food by combining, mixing, and heating ingredients. \n" +
                            "Example: he developed an interest in cooking.",
                    meaningExample = ""
                ),
                DictionaryMeaningItem(
                    meaning = "The practice or skill of preparing food by combining, mixing, and heating ingredients. \n" +
                            "Example: he developed an interest in cooking.",
                    meaningExample = ""
                )
            )

        )

        binding.recyclerViewDictionary.layoutManager = LinearLayoutManager(root.context)
        binding.recyclerViewDictionary.adapter = dictionaryMeaningItemsAdapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}