package com.example.mobileapplab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobileapplab1.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }
}