package com.example.fullnamecombiner

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

        // Find the views from the layout file
        val firstNameInput = findViewById<EditText>(R.id.firstNameInput)
        val lastNameInput = findViewById<EditText>(R.id.lastNameInput)
        val combineButton = findViewById<Button>(R.id.combineButton)

        //Set a click Listener for the button
        combineButton.setOnClickListener {

            // Get the text from the input fields
            val firstName = firstNameInput.text.toString()
            val lastName = lastNameInput.text.toString()

            //Checks if the fields are empty
            if (firstName.isBlank() || lastName.isBlank()) {
                Toast.makeText(this, "Please fill out both names.", Toast.LENGTH_SHORT).show()
            } else {

                // Create an intent to open the next activity
                val intent = Intent(this, DisplayActivity::class.java).apply {
                    putExtra("FIRST_NAME", firstName)
                    putExtra("LAST_NAME", lastName)
                }
                startActivity(intent)
            }
        }
    }
}