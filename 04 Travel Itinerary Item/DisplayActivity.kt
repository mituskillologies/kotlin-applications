package com.example.travelitineraryitem

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Link this code to the display screen layout (activity_display.xml).
        setContentView(R.layout.activity_display)

        // Find the text box on the screen where the message will be shown.
        val textViewMessage: TextView = findViewById(R.id.textViewMessage)

        // Retrieve the data passed from InputActivity using the shared unique key (EXTRA_DESTINATION).
        val destinationCity = intent.getStringExtra(InputActivity.EXTRA_DESTINATION)

        // Check if the city name was successfully passed.
        if (destinationCity != null) {
            // Build the final, personalized confirmation message.
            val travelMessage = "Your next trip is to $destinationCity!"
            // Show the message on the screen's text box.
            textViewMessage.text = travelMessage
        } else {
            // Show an error message if no city name was found in the Intent.
            textViewMessage.text = "Error: Destination not found."
        }
    }
}