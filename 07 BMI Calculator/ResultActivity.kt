package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Reference UI elements from the layout
        val bmiScoreTextView: TextView = findViewById(R.id.bmiScoreTextView)
        val bmiCategoryTextView: TextView = findViewById(R.id.bmiCategoryTextView)

        // Get the BMI value passed from InputActivity
        val bmi = intent.getDoubleExtra("BMI_RESULT", 0.0)

        // Format the BMI to one decimal place
        val formattedBmi = String.format("%.1f", bmi)

        // Display the formatted BMI score
        bmiScoreTextView.text = formattedBmi

        // Determine the weight status category using a 'when' statement
        val bmiCategory = when {
            bmi < 18.5 -> "Underweight"
            bmi < 25.0 -> "Normal weight"
            bmi < 30.0 -> "Overweight"
            else -> "Obesity"
        }

        // Display the determined category
        bmiCategoryTextView.text = bmiCategory
    }
}
