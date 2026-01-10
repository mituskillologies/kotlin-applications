package com.example.image_viewer_fa2

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class DisplayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        val imageView: ImageView = findViewById(R.id.image_view_display)
        val imageIdentifier = intent.getStringExtra("image_id")

        when (imageIdentifier) {
            "cat" -> imageView.setImageResource(R.drawable.cat_image)
            "dog" -> imageView.setImageResource(R.drawable.dog_image)
            "bird" -> imageView.setImageResource(R.drawable.bird_image)
        }
    }
}