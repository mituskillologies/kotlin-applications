package com.example.kotlin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PostActivity : AppCompatActivity() {

    private lateinit var authorTextView: TextView
    private lateinit var messageTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        // Initialize views
        authorTextView = findViewById(R.id.authorTextView)
        messageTextView = findViewById(R.id.messageTextView)

        // Get data from intent
        val username = intent.getStringExtra("USERNAME") ?: "Anonymous"
        val message = intent.getStringExtra("MESSAGE") ?: ""

        // Display the data
        authorTextView.text = "Posted by: $username"
        messageTextView.text = message
    }
}