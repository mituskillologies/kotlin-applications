package com.example.shoppinglist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.databinding.ActivityAddItemBinding

class AddItemActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAddItem.setOnClickListener {
            val itemName = binding.editTextItemName.text.toString().trim()
            val quantityStr = binding.editTextQuantity.text.toString().trim()

            if (validateInput(itemName, quantityStr)) {
                val quantity = quantityStr.toInt()
                val resultIntent = Intent().apply {
                    putExtra("ITEM_NAME", itemName)
                    putExtra("ITEM_QUANTITY", quantity)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish() // Close this activity and return to MainActivity
            }
        }

        binding.buttonCancel.setOnClickListener {
            finish() // Simply close the activity without sending data
        }
    }

    private fun validateInput(name: String, quantity: String): Boolean {
        return when {
            name.isBlank() -> {
                binding.textFieldLayoutItemName.error = "Item name cannot be empty"
                false
            }
            quantity.isBlank() -> {
                binding.textFieldLayoutQuantity.error = "Quantity cannot be empty"
                false
            }
            else -> {
                // Clear any previous errors
                binding.textFieldLayoutItemName.error = null
                binding.textFieldLayoutQuantity.error = null
                true
            }
        }
    }
}
