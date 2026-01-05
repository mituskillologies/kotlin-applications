package com.example.agegroup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class InputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val ageEditText = findViewById<EditText>(R.id.ageEditText)
        val checkButton = findViewById<Button>(R.id.checkButton)

        checkButton.setOnClickListener {
            val ageText = ageEditText.text.toString().trim()

            // Validate input
            if (ageText.isEmpty()) {
                Toast.makeText(this, "Please enter your age", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Convert to integer
            val age = ageText.toIntOrNull()
            
            if (age == null || age < 0) {
                Toast.makeText(this, "Please enter a valid age", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create intent and pass age to ResultActivity
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("age", age)
            startActivity(intent)
        }
    }
}