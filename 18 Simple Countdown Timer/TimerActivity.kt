package com.example.simplecountdowntimerfixed

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View // Import for setting View visibility
import androidx.appcompat.app.AppCompatActivity
import com.example.simplecountdowntimerfixed.databinding.ActivityTimerBinding

class TimerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Task 2: Get the Intent and extract the total seconds
        val totalSeconds = intent.getIntExtra("TOTAL_SECONDS", 0)

        if (totalSeconds > 0) {
            val totalMilliseconds = totalSeconds.toLong() * 1000

            // Task 2: Create a CountDownTimer (tick interval is 1000ms = 1 second)
            countDownTimer = object : CountDownTimer(totalMilliseconds, 1000) {

                // Task 2: Update the TextView on each tick
                override fun onTick(millisUntilFinished: Long) {
                    val secondsRemaining = millisUntilFinished / 1000
                    binding.textViewTimer.text = secondsRemaining.toString()
                }

                // Task 2: Display "Finished!" on completion
                override fun onFinish() {
                    binding.textViewTimer.text = "Finished!"
                    // NEW: Show the restart button
                    binding.buttonRestart.visibility = View.VISIBLE
                }
            }.start() // Task 2: Start the timer
        } else {
            binding.textViewTimer.text = "Error: Invalid Time"
        }

        // NEW: Set click listener for the restart button
        binding.buttonRestart.setOnClickListener {
            // Create an intent to go back to the SetupActivity
            val intent = Intent(this, SetupActivity::class.java).apply {
                // These flags clear the back stack, so the user can't just press Back
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            startActivity(intent)
            finish() // Close the current TimerActivity
        }
    }

    // Stop the timer if the user leaves the activity
    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}