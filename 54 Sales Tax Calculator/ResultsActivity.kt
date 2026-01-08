package com.example.salestaxcalculator

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_results)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val price = intent.getDoubleExtra("Price", 0.0)
        val salesTax = intent.getDoubleExtra("salesTax", 0.0)
        val totalPrice = intent.getDoubleExtra("totalPrice", 0.0)

        val priceTextView = findViewById<TextView>(R.id.textView9)
        val taxTextView = findViewById<TextView>(R.id.textView10)
        val totalTextView = findViewById<TextView>(R.id.textView11)

        priceTextView.text = "$" + String.format("%.2f", price)
        taxTextView.text = "$" + String.format("%.2f", salesTax)
        totalTextView.text = "$" + String.format("%.2f", totalPrice)
    }
}