package com.najrudinan.ticket

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val generateTicketButton = findViewById<Button>(R.id.generateTicketPageButton)

        generateTicketButton.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }
    }
}
