package com.example.mobileapplab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobileapplab1.databinding.ActivityTrainingFinishBinding

class TrainingFinishActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTrainingFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrainingFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBack.setOnClickListener {
            finish()
        }

        binding.buttonAgain.setOnClickListener {
            startActivity(Intent(this, TestActivity::class.java))
            finish()
        }

        binding.correctText.text = "Correct: ${intent!!.getStringExtra("correct").toString()}"
        binding.incorrectText.text = "Incorrect: ${intent!!.getStringExtra("incorrect").toString()}"
    }
}