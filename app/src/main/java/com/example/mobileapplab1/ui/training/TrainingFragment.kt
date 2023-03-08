package com.example.mobileapplab1.ui.training

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mobileapplab1.databinding.FragmentTrainingBinding

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

        val textView: TextView = binding.textTraining
        trainingViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}