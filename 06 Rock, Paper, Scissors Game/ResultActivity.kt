package com.example.rockpaperscissors

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rockpaperscissors.databinding.ActivityResultBinding
import com.google.android.material.button.MaterialButton

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val playAgainButton = binding.btnPlayAgain as MaterialButton
        playAgainButton.setBackgroundColor(Color.parseColor("#FDAAAA"))
        playAgainButton.strokeWidth = 6
        playAgainButton.strokeColor = android.content.res.ColorStateList.valueOf(Color.BLACK)

        // --- End of programmatic style ---

        val playerChoice = intent.getStringExtra("playerChoice") ?: "Rock"
        val computerChoice = intent.getStringExtra("computerChoice") ?: "Rock"

        val images = mapOf(
            "Rock" to R.drawable.rock,
            "Paper" to R.drawable.paper,
            "Scissors" to R.drawable.scissors
        )

        binding.imgPlayer.setImageResource(images[playerChoice]!!)
        binding.imgComputer.setImageResource(images[computerChoice]!!)

        // Determine winner and set result text and color
        when {
            playerChoice == computerChoice -> {
                binding.tvResult.text = "It's a Draw!"
                binding.tvResult.setTextColor(Color.parseColor("#9C27B0")) // Purple
            }
            (playerChoice == "Rock" && computerChoice == "Scissors") ||
                    (playerChoice == "Paper" && computerChoice == "Rock") ||
                    (playerChoice == "Scissors" && computerChoice == "Paper") -> {
                binding.tvResult.text = "You Win!"
                binding.tvResult.setTextColor(Color.parseColor("#4CAF50")) // Green
            }
            else -> {
                binding.tvResult.text = "You Lose!"
                binding.tvResult.setTextColor(Color.parseColor("#F44336")) // Red
            }
        }

        // Play Again button click listener
        binding.btnPlayAgain.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            // This flag tells GameActivity to show the choices directly
            intent.putExtra("showChoices", true)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish() // Finish this activity
        }
    }
}
