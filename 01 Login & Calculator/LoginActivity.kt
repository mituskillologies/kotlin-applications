package com.example.logincalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private val CORRECT_USERNAME = "admin"
    private val CORRECT_PASSWORD = "12345"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val enteredUsername = usernameEditText.text.toString()
            val enteredPassword = passwordEditText.text.toString()

            if (enteredUsername == CORRECT_USERNAME && enteredPassword == CORRECT_PASSWORD) {
                val intent = Intent(this, CalculatorActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Authentication Failure", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
