package com.example.kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var messageEditText: EditText
    private lateinit var postButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        usernameEditText = findViewById(R.id.usernameEditText)
        messageEditText = findViewById(R.id.messageEditText)
        postButton = findViewById(R.id.postButton)

        // Set click listener for post button
        postButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val message = messageEditText.text.toString().trim()

            // Validate input
            if (username.isEmpty() || message.isEmpty()) {
                Toast.makeText(
                    this,
                    "Username and message cannot be empty.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Create intent and pass data to PostActivity
                val intent = Intent(this, PostActivity::class.java)
                intent.putExtra("USERNAME", username)
                intent.putExtra("MESSAGE", message)
                startActivity(intent)
            }
        }
    }
}