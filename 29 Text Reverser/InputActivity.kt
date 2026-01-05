package com.example.textreverser

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class InputActivity : Activity() {

    private lateinit var editTextInput: EditText
    private lateinit var buttonReverse: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        // Initialize views
        editTextInput = findViewById(R.id.editTextInput)
        buttonReverse = findViewById(R.id.buttonReverse)

        // Set click listener for the Reverse button
        buttonReverse.setOnClickListener {
            val inputText = editTextInput.text.toString()

            // Validate input - check if empty
            if (inputText.isEmpty()) {
                Toast.makeText(
                    this,
                    "Please enter some text",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Create intent and pass the text to ResultActivity
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("INPUT_TEXT", inputText)
                startActivity(intent)
            }
        }
    }
}