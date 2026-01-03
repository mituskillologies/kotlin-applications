package com.example.temperatureconverterapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout for this activity, which is defined in 'activity_result.xml'
        setContentView(R.layout.activity_result)

        // 1. Get a reference to the TextView in our layout
        val resultTextView: TextView = findViewById(R.id.textView_result)

        // 2. Get the Fahrenheit value passed via the Intent's extras
        // The key must match the one used in ConverterActivity.
        // We provide a default value of 0.0 in case it's missing.
        val fahrenheitResult = intent.getDoubleExtra("com.example.tempconverter.FAHRENHEIT", 0.0)

        // 3. Format the number to show a maximum of two decimal places for a clean look
        val decimalFormat = DecimalFormat("#.##")
        val formattedFahrenheit = decimalFormat.format(fahrenheitResult)

        // 4. Create the final string to be displayed
        val resultString = "Result: $formattedFahrenheit°F"

        // 5. Set the formatted string as the text for our TextView
        resultTextView.text = resultString

        // Optional: Set a title for the activity bar
        title = "Conversion Result"
    }
}
