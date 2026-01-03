package com.example.simplepasswordgenerator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlin.random.Random

class DisplayActivity : AppCompatActivity() {

    // Store the keyword passed from InputActivity
    private lateinit var keyword: String
    // TextView to display generated password
    private lateinit var passwordTextView: TextView
    // TextView to display the entered keyword
    private lateinit var keywordTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout for this activity
        setContentView(R.layout.activity_display)

        // Initialize TextViews
        keywordTextView = findViewById(R.id.keywordTextView)
        passwordTextView = findViewById(R.id.passwordTextView)

        // Initialize buttons
        val generateAnotherButton = findViewById<Button>(R.id.generateAnotherButton)
        val generateNewKeywordButton = findViewById<Button>(R.id.generateNewKeywordButton)

        // Reference to the root layout for background color change
        val rootLayout= findViewById<LinearLayout>(R.id.rootLayout)
        // Button to toggle dark mode
        val darkModeButton= findViewById<Button>(R.id.darkModeButton)
        // Boolean flag to track dark mode state
        var isDark= false

        // Set click listener for dark mode toggle
        darkModeButton.setOnClickListener{
            isDark= !isDark
            if(isDark){
                // Apply dark mode colors
                rootLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.darkBg))
                keywordTextView.setTextColor(ContextCompat.getColor(this,R.color.darkText))
                passwordTextView.setTextColor(ContextCompat.getColor(this,R.color.darkText))
                generateAnotherButton.setTextColor(ContextCompat.getColor(this,R.color.darkText))
                generateNewKeywordButton.setTextColor(ContextCompat.getColor(this,R.color.darkText))
            }
            else{
                // Apply light mode colors
                rootLayout.setBackgroundColor(ContextCompat.getColor(this,R.color.lightBg))
                keywordTextView.setTextColor(ContextCompat.getColor(this,R.color.lightText))
                passwordTextView.setTextColor(ContextCompat.getColor(this,R.color.lightText))
                generateAnotherButton.setTextColor(ContextCompat.getColor(this,R.color.lightText))
                generateNewKeywordButton.setTextColor(ContextCompat.getColor(this,R.color.lightText))
            }
        }

        // Safely get the keyword passed from InputActivity
        keyword = intent.getStringExtra("keyword") ?: ""

        // Display the keyword and generate initial password
        displayKeyAndPass()

        // Generate a new password with the same keyword
        generateAnotherButton.setOnClickListener {
            passwordTextView.text = generatePassword()
        }

        // Go back to InputActivity to enter a new keyword
        generateNewKeywordButton.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }
    }

    // Function to display the keyword and initial password
    private fun displayKeyAndPass() {
        keywordTextView.text = "Your keyword: $keyword"
        passwordTextView.text = generatePassword()
    }

    // Function to generate a password using the keyword, random word, number, and symbol
    private fun generatePassword(): String {
        // List of symbols to use in the password
        val symbols = listOf("!", "@", "#", "$", "%", "^", "&", "*", "?", "~")
        // List of words to combine with the keyword
        val words = listOf("strongPass", "whyTho", "hacMe", "ktR0cks")
        // Randomly pick a word, number, and symbol
        val randomWord = words.random()
        val randomNum = Random.nextInt(0, 255)
        val randomSymbol = symbols.random()

        // Alternate the pattern of the password based on even/odd number
        if (randomNum % 2 == 0)
            return "$randomSymbol$keyword$randomWord$randomNum"
        return "$randomWord$randomNum$keyword$randomSymbol"
    }
}
