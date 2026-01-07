package com.najrudinan.ticket

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class InputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val eventNameEditText = findViewById<TextInputEditText>(R.id.eventNameEditText)
        val generateTicketButton = findViewById<Button>(R.id.generateTicketButton)

        generateTicketButton.setOnClickListener {
            val eventName = eventNameEditText.text.toString().trim()
            if (eventName.isNotEmpty()) {
                val intent = Intent(this, TicketActivity::class.java)
                intent.putExtra("eventName", eventName)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Please enter an event name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
