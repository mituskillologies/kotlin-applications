package com.example.fullnamecombiner

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_display)

        // Find the TextView from the layout
        val fullNameDisplay = findViewById<TextView>(R.id.fullNameDisplay)

        // Get the data sent from the intent
            val firstName = intent.getStringExtra("FIRST_NAME") ?: ""
            val lastName = intent.getStringExtra("LAST_NAME") ?: ""

        // Combine the first and last name
        val fullName = "$firstName $lastName"
        fullNameDisplay.text = "Full Name: $fullName"
    }
}