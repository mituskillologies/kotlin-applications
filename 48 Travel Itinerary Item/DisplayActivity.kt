package com.example.traveltenaryitem

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * DisplayActivity
 * Shows the planned destination in a sentence and displays a travel-themed image.
 */
class DisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        // Get references to views
        val imageTravel = findViewById<ImageView>(R.id.ivTravel)
        val textDestination = findViewById<TextView>(R.id.tvMessage)

        // Retrieve the destination passed via Intent extras
        val destination = intent.getStringExtra("destination") ?: "somewhere"

        // Set the travel message
        textDestination.text = "Your next trip is to $destination!"

        // The travel icon is already set in the layout XML using android:src="@drawable/ic_travel"
    }
}