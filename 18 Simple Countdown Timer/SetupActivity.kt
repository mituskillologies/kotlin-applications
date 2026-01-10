package com.example.simplecountdowntimerfixed

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.simplecountdowntimerfixed.databinding.ActivitySetupBinding

class SetupActivity : AppCompatActivity() {

    // 1. Declare the View Binding object
    private lateinit var binding: ActivitySetupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 2. Initialize View Binding and set the content view
        binding = ActivitySetupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Task 1: Set an onClickListener for the "Start Timer" button
        binding.buttonStart.setOnClickListener {
            // Retrieve the text from the EditText using View Binding
            val secondsText = binding.editTextSeconds.text.toString()

            // Task 1: Perform validation
            if (secondsText.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter a valid number of seconds.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val totalSeconds = secondsText.toIntOrNull()

            if (totalSeconds == null || totalSeconds <= 0) {
                // Show a Toast message if input is invalid
                Toast.makeText(this, "Please enter a valid number of seconds.", Toast.LENGTH_SHORT).show()
            } else {
                // Task 1: Create an Intent to launch TimerActivity
                val intent = Intent(this, TimerActivity::class.java).apply {
                    // Task 1: Pass the number of seconds to the next activity
                    putExtra("TOTAL_SECONDS", totalSeconds)
                }
                startActivity(intent)
            }
        }
    }
}