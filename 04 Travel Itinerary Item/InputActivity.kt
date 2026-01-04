package com.example.travelitineraryitem
// Imports necessary Android classes for creating the activity, handling intents,
// managing bundles (saved state), using buttons and text fields, displaying pop-up messages (Toast),
// and extending the base activity class.
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// Define the main input activity, extending AppCompatActivity for backward compatibility
class InputActivity : AppCompatActivity() {

    // companion object holds static members
    companion object {
        // Create a unique label (key) to attach the destination city data to the screen-switching instruction (Intent).
        const val EXTRA_DESTINATION = "com.example.travelitineraryitem.DESTINATION"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input) // Links to the renamed XML

        // 1. UI Element Initialization
        val editTextDestination: EditText = findViewById(R.id.editTextDestination)
        val buttonPlanTrip: Button = findViewById(R.id.buttonPlanTrip)

        buttonPlanTrip.setOnClickListener {
            // Get the text entered by the user, convert it to a string, and trim whitespace.
            val destinationCity = editTextDestination.text.toString().trim()

            if (destinationCity.isEmpty()) {
                // Check if the input field is empty after trimming whitespace.
                Toast.makeText(this, "Please enter a destination city.", Toast.LENGTH_LONG).show()
            } else {
                // Create a clear instruction (Intent) to start the DisplayActivity
                val intent = Intent(this, DisplayActivity::class.java).apply {
                    // Put the entered city (destinationCity) into the Intent,
                    // using EXTRA_DESTINATION as a unique label (key).
                    putExtra(EXTRA_DESTINATION, destinationCity)
                }
                // Start the New Activity
                startActivity(intent)
            }
        }
    }
}