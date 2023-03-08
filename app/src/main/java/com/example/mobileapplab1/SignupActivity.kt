package com.example.mobileapplab1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        findViewById<Button>(R.id.buttonSignup).setOnClickListener {
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
        val fieldName = findViewById<TextInputEditText>(R.id.signup_input_name)
        val fieldEmail = findViewById<TextInputEditText>(R.id.signup_input_email)
        val fieldPassword = findViewById<TextInputEditText>(R.id.signup_input_password)
        return (fieldName.text.toString().isNotEmpty() &&
                fieldEmail.text.toString().isNotEmpty() &&
                fieldPassword.text.toString().isNotEmpty())
    }

}