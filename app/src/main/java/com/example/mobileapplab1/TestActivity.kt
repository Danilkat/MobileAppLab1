package com.example.mobileapplab1

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.mobileapplab1.databinding.ActivityTestBinding
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding
    private lateinit var db: DictionaryDatabase

    private var allWords =  arrayListOf<Pair<String, String>>()
    private lateinit var currWord: Pair<String, String>

    private var size = 0
    private var rightAnswers = 0
    private var position = 0

    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            binding.root.context,
            DictionaryDatabase::class.java, "dictionary-db"
        )
            .fallbackToDestructiveMigration()
            .build()

        binding.testOption1.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                it.setBackgroundResource(R.drawable.selected_answer_background)
                delay(700)
                checkAnswer(binding.testOption1.text.toString())
            }
        }

        binding.testOption2.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                it.setBackgroundResource(R.drawable.selected_answer_background)
                delay(700)
                checkAnswer(binding.testOption2.text.toString())
            }
        }

        binding.testOption3.setOnClickListener {
            lifecycleScope.launch(Dispatchers.Main) {
                it.setBackgroundResource(R.drawable.selected_answer_background)
                delay(700)
                checkAnswer(binding.testOption3.text.toString())
            }
        }

        binding.buttonBack.setOnClickListener {
            finish()
        }

        testStart()
    }

    private fun testStart() {
        position = 0

        lifecycleScope.launch(Dispatchers.IO) {
            val queryRes = db.dictionaryDao().getUniqueMeanings()
            Log.d("", queryRes.toString())
            val wordsForTest = ArrayList(db.dictionaryDao().getUniqueMeanings())

            wordsForTest.forEach {
                allWords.add(Pair(it.name, it.v_meaning))
            }

            Log.d("", allWords.toString())
            size = wordsForTest.size

            withContext(Dispatchers.Main) {
                answerTestLayout()
            }
        }
    }

    private fun answerTestLayout() {
        timerStart()

        if (size > 10) size = 10

        if (position < size) {
            currWord = allWords[position]
            val wrongWords = allWords.toMutableList().apply {
                removeAt(position)
            }
            wrongWords.shuffle()
            binding.positionText.text = "${position + 1} of $size"
            binding.questionText.text = currWord.second

            var answerVariants = mutableListOf("cooking", "smiling", currWord.first)
            if (size > 2) {
                val middle: Int = (size - 1) / 2
                answerVariants = mutableListOf(
                    currWord.first,
                    wrongWords[(0 until middle).random()].first,
                    wrongWords[(middle until size - 1).random()].first
                )
            }
            answerVariants.shuffle()
            binding.testOption1.text = "A. ${answerVariants[0]}"
            binding.testOption2.text = "B. ${answerVariants[1]}"
            binding.testOption3.text = "C. ${answerVariants[2]}"
        }
        else {
            val intent = Intent(this@TestActivity, TrainingFinishActivity::class.java)
            intent.putExtra("correct", rightAnswers.toString())
            intent.putExtra("incorrect", (size - rightAnswers).toString())
            startActivity(intent)
            finish()
        }
    }

    private fun checkAnswer(word : String) {
        timer?.cancel()
        binding.testOption1.setBackgroundResource(R.drawable.input_background)
        binding.testOption2.setBackgroundResource(R.drawable.input_background)
        binding.testOption3.setBackgroundResource(R.drawable.input_background)

        val t = position
        Log.d("", word + " " + currWord.first)
        val updWord = currWord.first
        if (word.removeRange(0, 3).lowercase() == currWord.first.lowercase()){
            rightAnswers++
            lifecycleScope.launch(Dispatchers.IO) {
                db.dictionaryDao().plusLearningSpeed(updWord)
            }
        } else {
            lifecycleScope.launch(Dispatchers.IO) {
                db.dictionaryDao().minusLearningSpeed(updWord)
            }
        }
        position++
        answerTestLayout()
    }

    private fun timerStart() {
        binding.progressBarLinear.max = 30000
        binding.progressBarLinear.min = 0

        if (position >= size) {
            timer?.cancel()
            return
        }

        timer?.cancel()
        timer = object : CountDownTimer(30000, 1000) {
            override fun onTick(p0: Long) {
                val seconds = 5 - TimeUnit.MILLISECONDS.toSeconds(p0).toInt()
                val animator = ObjectAnimator.ofInt(binding.progressBarLinear, "progress", seconds*1000,(seconds+1)*1000)
                animator.duration = 1000
                animator.start()
            }

            override fun onFinish() {
                checkAnswer("   ")
            }
        }.start()
    }
}