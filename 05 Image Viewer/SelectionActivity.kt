package com.example.image_viewer_fa2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)

        val buttonCat: Button = findViewById(R.id.button_cat)
        val buttonDog: Button = findViewById(R.id.button_dog)
        val buttonBird: Button = findViewById(R.id.button_bird)

        buttonCat.setOnClickListener {
            val intent = Intent(this, DisplayActivity::class.java)
            intent.putExtra("image_id", "cat")
            startActivity(intent)
        }

        buttonDog.setOnClickListener {
            val intent = Intent(this, DisplayActivity::class.java)
            intent.putExtra("image_id", "dog")
            startActivity(intent)
        }

        buttonBird.setOnClickListener {
            val intent = Intent(this, DisplayActivity::class.java)
            intent.putExtra("image_id", "bird")
            startActivity(intent)
        }
    }
}