//package com.example.temp converter
package com.example.temperatureconverterapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


// Key used to pass the converted Fahrenheit value to the ResultActivity
const val EXTRA_FAHRENHEIT = "com.example.tempconverter.FAHRENHEIT"

class ConverterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout for this activity
        setContentView(R.layout.activity_converter)

        // 1. Get references to UI elements
        val editTextCelsius = findViewById<EditText>(R.id.editText_celsius)
        val convertButton = findViewById<Button>(R.id.button_convert)

        // 2. Set an onClickListener for the "Convert" button
        convertButton.setOnClickListener {
            // Get the text input from the EditText as a String
            val celsiusText = editTextCelsius.text.toString()

            // 3. Validate that the input is not empty
            if (celsiusText.isBlank()) {
                // If input is empty, show a Toast message
                Toast.makeText(this, "Please enter a temperature in Celsius.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Exit the click listener
            }

            try {
                // 4. Convert the string to a Double
                val celsius = celsiusText.toDouble()

                // 5. Calculate the temperature in Fahrenheit using the formula: F = (C * 9/5) + 32
                val fahrenheit = (celsius * 9.0 / 5.0) + 32.0

                // 6. Create an Intent to launch the ResultActivity
                val intent = Intent(this, ResultActivity::class.java)

                // 7. Use putExtra() to pass the calculated Fahrenheit value
                intent.putExtra(EXTRA_FAHRENHEIT, fahrenheit)

                // 8. Start the ResultActivity
                startActivity(intent)

            } catch (e: NumberFormatException) {
                // Handle cases where the input is not a valid number (shouldn't happen with inputType="numberDecimal", but good practice)
                Toast.makeText(this, "Invalid number entered.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
