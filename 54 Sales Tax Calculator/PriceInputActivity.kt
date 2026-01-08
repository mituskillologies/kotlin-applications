package com.example.salestaxcalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PriceInputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_price_input)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val editText = findViewById<EditText>(R.id.editTextNumberDecimal2)
        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val inputValue = editText.text.toString()
            val Price = inputValue.toDoubleOrNull() ?: 0.0
            val salesTax = Price * 0.08
            val totalPrice = Price + salesTax

            val intent = Intent(this, ResultsActivity::class.java).apply {
                putExtra("Price", Price)
                putExtra("salesTax", salesTax)
                putExtra("totalPrice", totalPrice)
            }

            startActivity(intent)
        }
    }
}