
package com.example.ludoking

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class DiceRollerActivity : AppCompatActivity() {

    // currentRoll holds the current visible dice value
    // previousRoll holds the last roll before the current one (initially -1 => none)
    private var currentRoll: Int = 1
    private var previousRoll: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dice_roller_activity)

        val ivDice = findViewById<ImageView>(/* id = */ R.id.ivDice)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val btnRoll = findViewById<Button>(R.id.btnRoll)
        val btnShowLast = findViewById<Button>(R.id.btnShowLast)

        // initial UI
        ivDice.setImageResource(getDrawableForRoll(currentRoll))
        tvResult.text = "You rolled: $currentRoll"

        btnRoll.setOnClickListener {
            // store current as previous, then generate new current
            previousRoll = currentRoll
            val newRoll = Random.nextInt(1, 7)
            currentRoll = newRoll

            // update UI
            ivDice.setImageResource(getDrawableForRoll(currentRoll))
            tvResult.text = "You rolled: $currentRoll"
        }

        btnShowLast.setOnClickListener {
            if (previousRoll == -1) {
                // no previous roll existed
                Toast.makeText(this, "No previous roll available yet.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, LastRollActivity::class.java)
                intent.putExtra("LAST_ROLL", previousRoll)
                startActivity(intent)
            }
        }
    }

    private fun getDrawableForRoll(roll: Int): Int {
        return when (roll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}

