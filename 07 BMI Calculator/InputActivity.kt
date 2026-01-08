package com.example.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class InputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        //Reference UI elements from the layout
        val weightEditText: EditText = findViewById(R.id.weightEditText)
        val heightEditText: EditText = findViewById(R.id.heightEditText)
        val calculateButton: Button = findViewById(R.id.calculateButton)

        //Set a click listener on the button to perform the calculation
        calculateButton.setOnClickListener {
            //Getting the text from EditTexts
            val weightStr = weightEditText.text.toString()
            val heightStr = heightEditText.text.toString()

            //Input validation: Check if fields are empty(Handling Issues)
            if (weightStr.isNotEmpty() && heightStr.isNotEmpty()) {
                // Convert strings to Double for calculation
                val weight = weightStr.toDouble()
                val height = heightStr.toDouble()

                //Ensure height is not zero to avoid any type of error
                if (height > 0) {
                    //Calculate BMI
                    val bmi = weight / (height * height)

                    //Create an Intent to start the ResultActivity
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra("BMI_RESULT", bmi)
                    startActivity(intent)
                } else {
                    //Show an error message if height is zero
                    Toast.makeText(this, "Height must be greater than zero", Toast.LENGTH_SHORT).show()
                }
            } else {
                //Show a toast message if either field is empty
                Toast.makeText(this, "Please enter both weight and height", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
