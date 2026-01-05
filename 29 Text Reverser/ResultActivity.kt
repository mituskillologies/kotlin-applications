package com.example.textreverser

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : Activity() {

    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Initialize view
        textViewResult = findViewById(R.id.textViewResult)

        // Get the Intent and extract the string passed from InputActivity
        val inputText = intent.getStringExtra("INPUT_TEXT") ?: ""

        // Reverse the string using Kotlin's built-in .reversed() function
        val reversedText = inputText.reversed()

        // Update the TextView to show "Reversed Text: [Reversed String]"
        textViewResult.text = "Reversed Text: $reversedText"
    }
}