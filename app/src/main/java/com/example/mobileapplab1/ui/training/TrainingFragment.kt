package com.example.mobileapplab1.ui.training

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.mobileapplab1.DictionaryDatabase
import com.example.mobileapplab1.R
import com.example.mobileapplab1.SignupActivity
import com.example.mobileapplab1.TestActivity
import com.example.mobileapplab1.databinding.FragmentTrainingBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit

class TrainingFragment : Fragment() {

    private var _binding: FragmentTrainingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val trainingViewModel =
            ViewModelProvider(this).get(TrainingViewModel::class.java)

        _binding = FragmentTrainingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val db = Room.databaseBuilder(
            binding.root.context,
            DictionaryDatabase::class.java, "dictionary-db"
        )
            .fallbackToDestructiveMigration()
            .build()
        var text = ""
        lifecycleScope.launch(Dispatchers.IO) {
            val size = db.dictionaryDao().getCount()

            withContext(Dispatchers.Main) {
                if (size != 0) {
                    text = "There are $size words \n in your Dictionary. \n\n Start the Training?"
                    val spannableString = SpannableString(text)
                    val foregroundColorSpanCyan = ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.primary))
                    spannableString.setSpan(foregroundColorSpanCyan, 10, 10 + size.toString().length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

                    binding.countWordText.text = spannableString
                } else {
                    text = "Add words to the dictionary"
                    binding.countWordText.text = text
                }

                binding.buttonStartTraining.setOnClickListener {
                    if (size != 0) {
                        binding.buttonStartTraining.visibility = View.INVISIBLE
                        binding.progressBarCircle.visibility = View.VISIBLE
                        binding.progressText.visibility = View.VISIBLE
                        startTraining()

                    } else {
                        AlertDialog.Builder(binding.root.context)
                            .setTitle("Error")
                            .setMessage("Add words to the dictionary")
                            .show()
                    }
                }
            }

        }

        return root
    }

    var colors = arrayOf(R.color.training_5,
        R.color.training_4,
        R.color.training_3,
        R.color.training_2,
        R.color.training_1,
        R.color.training_GO)

    private fun startTraining() {
        var timer = object: CountDownTimer(6000, 1000) {
            override fun onTick(p0: Long) {
                val seconds = TimeUnit.MILLISECONDS.toSeconds(p0).toInt()
                Log.d("", seconds.toString())
                binding.progressText.text = if (seconds != 0) seconds.toString() else "GO!"
                binding.progressText.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        colors[5 - seconds]
                    )
                )

                val animator = ObjectAnimator.ofInt(binding.progressBarCircle, "progress", 0, 100)
                animator.duration = 1000
                animator.start()
            }

            override fun onFinish() {
                startActivity(Intent(binding.root.context.applicationContext, TestActivity::class.java))
                getActivity()?.finish()
            }
        }.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}