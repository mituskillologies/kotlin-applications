package com.example.myfirstapp

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class DisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        // Get references to UI elements
        val rootLayout: ConstraintLayout = findViewById(R.id.root_layout)
        val textViewHexCode: TextView = findViewById(R.id.text_view_hex_code)

        // 1. Extract the color integer using the constant key from ColorPickerActivity
        // Use Color.BLACK (0) as the default if the color is missing.
        val colorInt = intent.getIntExtra(ColorPickerActivity.EXTRA_COLOR_INT, Color.BLACK)

        // 2. Set the background color of the root layout
        rootLayout.setBackgroundColor(colorInt)

        // 3. Format the color integer into a hex string (#RRGGBB format)
        val hexCode = String.format("#%06X", (0xFFFFFF and colorInt))

        // 4. Display the hex code
        textViewHexCode.text = hexCode

        // 5. Optional: Adjust text color for contrast (recommended)
        // Check if the background is dark (R+G+B sum < midpoint of 765)
        if (Color.red(colorInt) +
            Color.green(colorInt) +
            Color.blue(colorInt) < 382) {
            textViewHexCode.setTextColor(Color.WHITE)
        } else {
            textViewHexCode.setTextColor(Color.BLACK)
        }
    }
}