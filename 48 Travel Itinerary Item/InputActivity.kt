package com.example.traveltenaryitem

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

/**
 * InputActivity
 * Collects a destination city from the user and starts DisplayActivity.
 */
class InputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        // Get references to views
        val editDestination = findViewById<EditText>(R.id.editDestination)
        val btnPlan = findViewById<Button>(R.id.btnPlan)

        // Handle button click
        btnPlan.setOnClickListener {
            val city = editDestination.text.toString().trim()

            if (city.isEmpty()) {
                // Show a toast when input is empty
                Toast.makeText(this, "Please enter a city name.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create intent to start DisplayActivity with the destination as extra
            val intent = Intent(this, DisplayActivity::class.java).apply {
                putExtra("destination", city)
            }
            startActivity(intent)
        }
    }
}
