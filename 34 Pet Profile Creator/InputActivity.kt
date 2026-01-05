package com.example.petprofilecreatorr

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class InputActivity : AppCompatActivity() {

    // Declare UI components
    private lateinit var petNameEditText: EditText
    private lateinit var petSpeciesEditText: EditText
    private lateinit var createProfileButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        // Initialize UI components by finding them in the layout
        petNameEditText = findViewById(R.id.petNameEditText)
        petSpeciesEditText = findViewById(R.id.petSpeciesEditText)
        createProfileButton = findViewById(R.id.createProfileButton)

        // Set onClickListener for the Create Profile button
        createProfileButton.setOnClickListener {
            // Retrieve text from both EditText fields using getText()
            val petName = petNameEditText.getText().toString().trim()
            val petSpecies = petSpeciesEditText.getText().toString().trim()

            // Validate that neither field is empty
            if (petName.isEmpty()) {
                Toast.makeText(this, "Please enter pet's name", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (petSpecies.isEmpty()) {
                Toast.makeText(this, "Please enter pet's species", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create an Intent to launch ProfileActivity
            val intent = Intent(this, ProfileActivity::class.java)

            // Pass the pet's name and species as extras
            intent.putExtra("PET_NAME", petName)
            intent.putExtra("PET_SPECIES", petSpecies)

            // Start the ProfileActivity
            startActivity(intent)
        }
    }
}