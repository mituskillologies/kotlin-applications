package com.example.petprofilecreatorr

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    // Declare UI components
    private lateinit var petIconImageView: ImageView
    private lateinit var profileTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Initialize UI components
        petIconImageView = findViewById(R.id.petIconImageView)
        profileTextView = findViewById(R.id.profileTextView)

        // Get the Intent that launched this activity
        val intent = intent

        // Extract the pet's name and species from the Intent extras
        val petName = intent.getStringExtra("PET_NAME") ?: "Unknown"
        val petSpecies = intent.getStringExtra("PET_SPECIES") ?: "Unknown"

        // Combine the strings into a formatted message
        val profileMessage = "$petName the $petSpecies"

        // Set the TextView's text to display the formatted profile
        profileTextView.text = profileMessage
    }
}