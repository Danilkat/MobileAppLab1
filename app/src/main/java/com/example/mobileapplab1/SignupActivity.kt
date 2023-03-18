package com.example.mobileapplab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import com.example.mobileapplab1.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.buttonSignup.setOnClickListener {
            if (fieldsNotEmpty()) {
                startActivity(Intent(this@SignupActivity, NavigationActivity::class.java))
                finish()
                return@setOnClickListener
            }

            AlertDialog.Builder(this)
                .setTitle(getString(R.string.loginFail))
                .setMessage(R.string.loginFailEmpty)
                .show()
        }
    }

    private fun fieldsNotEmpty(): Boolean {
        val fieldName = binding.signupInputName
        val fieldEmail = binding.signupInputEmail
        val fieldPassword = binding.signupInputPassword
        return (fieldName.text.toString().isNotEmpty() &&
                fieldEmail.text.toString().isNotEmpty() &&
                fieldPassword.text.toString().isNotEmpty())
    }

}