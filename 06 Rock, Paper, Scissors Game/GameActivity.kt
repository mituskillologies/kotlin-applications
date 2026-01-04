package com.example.rockpaperscissors

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.rockpaperscissors.databinding.ActivityGameBinding
import com.google.android.material.button.MaterialButton

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val playButton = binding.btnPlay as MaterialButton
        playButton.setBackgroundColor(Color.parseColor("#FDAAAA"))
        playButton.strokeWidth = 6
        playButton.strokeColor = android.content.res.ColorStateList.valueOf(Color.BLACK)

        // --- End of programmatic style ---

        // Handle the "Play Again" intent
        val showChoices = intent.getBooleanExtra("showChoices", false)
        if (showChoices) {
            showChoiceScreen()
        } else {
            showPlayButton()
        }

        binding.btnPlay.setOnClickListener {
            showChoiceScreen()
        }

        binding.btnRock.setOnClickListener { openResult("Rock") }
        binding.btnPaper.setOnClickListener { openResult("Paper") }
        binding.btnScissors.setOnClickListener { openResult("Scissors") }
    }

    private fun showPlayButton() {
        binding.btnPlay.visibility = View.VISIBLE
        binding.logo.visibility = View.VISIBLE
        binding.choicesContainer.visibility = View.GONE
    }

    private fun showChoiceScreen() {
        binding.btnPlay.visibility = View.GONE
        binding.logo.visibility = View.GONE // Optionally hide logo to give more space
        binding.choicesContainer.visibility = View.VISIBLE
    }

    private fun openResult(playerChoice: String) {
        val choices = listOf("Rock", "Paper", "Scissors")
        val computerChoice = choices.random()

        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("playerChoice", playerChoice)
        intent.putExtra("computerChoice", computerChoice)
        startActivity(intent)

        finish()
    }
}
