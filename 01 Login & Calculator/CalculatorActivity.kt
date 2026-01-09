package com.example.logincalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val number1EditText = findViewById<EditText>(R.id.number1EditText)
        val number2EditText = findViewById<EditText>(R.id.number2EditText)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        
        val addButton = findViewById<Button>(R.id.addButton)
        val subtractButton = findViewById<Button>(R.id.subtractButton)
        val multiplyButton = findViewById<Button>(R.id.multiplyButton)
        val divideButton = findViewById<Button>(R.id.divideButton)

        addButton.setOnClickListener {
            performOperation(number1EditText, number2EditText, resultTextView, "add")
        }

        subtractButton.setOnClickListener {
            performOperation(number1EditText, number2EditText, resultTextView, "subtract")
        }

        multiplyButton.setOnClickListener {
            performOperation(number1EditText, number2EditText, resultTextView, "multiply")
        }

        divideButton.setOnClickListener {
            performOperation(number1EditText, number2EditText, resultTextView, "divide")
        }
    }

    private fun performOperation(
        num1EditText: EditText,
        num2EditText: EditText,
        resultTextView: TextView,
        operation: String
    ) {
        val num1String = num1EditText.text.toString()
        val num2String = num2EditText.text.toString()

        if (num1String.isEmpty() || num2String.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val num1 = num1String.toDouble()
            val num2 = num2String.toDouble()
            var result = 0.0

            when (operation) {
                "add" -> result = num1 + num2
                "subtract" -> result = num1 - num2
                "multiply" -> result = num1 * num2
                "divide" -> {
                    if (num2 == 0.0) {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                        return
                    }
                    result = num1 / num2
                }
            }

            resultTextView.text = "Result: $result"
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Invalid number format", Toast.LENGTH_SHORT).show()
        }
    }
}
