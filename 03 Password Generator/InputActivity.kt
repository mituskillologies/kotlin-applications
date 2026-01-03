package com.example.simplepasswordgenerator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat

class InputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout for this activity
        setContentView(R.layout.activity_input)

        // Reference to the EditText where user enters the keyword
        val keywordInput = findViewById<EditText>(R.id.keywordEditText)
        // Reference to the Button used to generate the password
        val generateButton = findViewById<Button>(R.id.generateButton)

        // Reference to the root layout for changing background color
        val rootLayout= findViewById<LinearLayout>(R.id.rootLayout)
        // Reference to the Button used to toggle dark mode
        val darkModeButton= findViewById<Button>(R.id.darkModeButton)
        // Boolean flag to track dark mode state
        var isDark= false

        // Set click listener for dark mode toggle button
        darkModeButton.setOnClickListener{
            // Toggle the dark mode state
            isDark= !isDark
            if(isDark){
                // Apply dark mode colors
                rootLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.darkBg))
                keywordInput.setTextColor(ContextCompat.getColor(this,R.color.darkText))
                generateButton.setTextColor(ContextCompat.getColor(this,R.color.darkText))
            }
            else{
                // Revert to light mode colors
                rootLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.lightBg))
                keywordInput.setTextColor(ContextCompat.getColor(this,R.color.lightText))
               generateButton.setTextColor(ContextCompat.getColor(this,R.color.lightText))
            }
        }

        // Set click listener for the generate password button
        generateButton.setOnClickListener {
            // Get the entered keyword and remove leading/trailing spaces
            val keyword= keywordInput.text.toString().trim()
            // Check if keyword length is less than 4 characters
            if(keyword.length < 4){
                // Show a warning message if keyword is too short
                Toast.makeText(this, "Keyword must be at least 4 characters!", Toast.LENGTH_SHORT).show()
            }
            else{
                // Create an Intent to navigate to DisplayActivity
                val intent = Intent(this, DisplayActivity::class.java)
                // Pass the keyword to the next activity
                intent.putExtra("keyword", keyword)
                // Start DisplayActivity
                startActivity(intent)
            }
        }
    }
}
