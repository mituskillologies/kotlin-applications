package com.example.agegroup

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        // Get the age from the intent
        val age = intent.getIntExtra("age", -1)

        // Determine age group using when statement
        val ageGroup = when {
            age < 13 -> "Child"
            age in 13..17 -> "Teenager"
            age >= 18 -> "Adult"
            else -> "Invalid age"
        }

        // Set the result text
        resultTextView.text = "Age Group: $ageGroup"
    }
}