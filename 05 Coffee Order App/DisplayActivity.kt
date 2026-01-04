package com.example.coffeeorder

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DisplayActivity : AppCompatActivity() {

    private lateinit var coffeeImageView: ImageView
    private lateinit var confirmationTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        coffeeImageView = findViewById(R.id.coffeeImageView)
        confirmationTextView = findViewById(R.id.confirmationTextView)

        val name = intent.getStringExtra("USER_NAME") ?: "Customer"
        val confirmationMessage = "An order for $name is ready!"
        confirmationTextView.text = confirmationMessage
    }
}
