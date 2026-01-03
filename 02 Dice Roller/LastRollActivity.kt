package com.example.ludoking

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LastRollActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last_roll)

        // References to views
        val tvLast = findViewById<TextView>(R.id.tvLastRoll)
        val ivLastDice = findViewById<ImageView>(R.id.ivLastDice)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Get last roll from intent
        val lastRoll = intent?.getIntExtra("LAST_ROLL", -1) ?: -1

        if (lastRoll in 1..6) {
            tvLast.text = "The last roll was: $lastRoll"

            // Set dice image based on value
            val diceDrawable = when (lastRoll) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                6 -> R.drawable.dice_6
                else -> R.drawable.dice_1
            }
            ivLastDice.setImageResource(diceDrawable)
        } else {
            tvLast.text = "No last roll available."
            ivLastDice.setImageResource(R.drawable.dice_1) // default dice image
        }

        // Back button
        btnBack.setOnClickListener { finish() }
    }
}
