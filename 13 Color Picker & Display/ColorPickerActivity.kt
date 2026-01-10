package com.example.myfirstapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ColorPickerActivity : AppCompatActivity() {

    // Key for passing the color integer to DisplayActivity
    companion object {
        const val EXTRA_COLOR_INT = "com.example.myfirstapp.COLOR_INT"
    }

    private lateinit var editTextRed: EditText
    private lateinit var editTextGreen: EditText
    private lateinit var editTextBlue: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color_picker)

        // Get references to UI elements
        editTextRed = findViewById(R.id.edit_text_red)
        editTextGreen = findViewById(R.id.edit_text_green)
        editTextBlue = findViewById(R.id.edit_text_blue)
        val buttonShowColor: Button = findViewById(R.id.button_show_color)

        // Set the click listener
        buttonShowColor.setOnClickListener {
            processColorInput()
        }
    }

    /**
     * Retrieves text, validates input (empty and range 0-255), and launches DisplayActivity.
     */
    private fun processColorInput() {
        val strRed = editTextRed.text.toString().trim()
        val strGreen = editTextGreen.text.toString().trim()
        val strBlue = editTextBlue.text.toString().trim()

        // 1. Check if any field is empty
        if (strRed.isEmpty() || strGreen.isEmpty() || strBlue.isEmpty()) {
            Toast.makeText(this, "Please fill all RGB fields.", Toast.LENGTH_SHORT).show()
            return
        }

        // Convert strings to integers for number and range validation
        val red: Int? = strRed.toIntOrNull()
        val green: Int? = strGreen.toIntOrNull()
        val blue: Int? = strBlue.toIntOrNull()

        // 2. Check if values are valid numbers between 0 and 255
        if (red == null || green == null || blue == null ||
            red !in 0..255 || green !in 0..255 || blue !in 0..255) {

            Toast.makeText(this, "Please enter valid numbers between 0 and 255 for all fields.", Toast.LENGTH_LONG).show()
            return
        }

        // 3. Validation passed: Create color integer
        val colorInt: Int = Color.rgb(red, green, blue)

        // 4. Create and launch Intent
        val intent = Intent(this, DisplayActivity::class.java).apply {
            putExtra(EXTRA_COLOR_INT, colorInt)
        }
        startActivity(intent)
    }
}