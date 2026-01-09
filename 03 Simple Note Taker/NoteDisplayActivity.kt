package com.example.simplenotetaker

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NoteDisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_display)

        val titleView: TextView = findViewById(R.id.edit_text_title_display)
        val bodyView: TextView = findViewById(R.id.edit_text_body_display)

        val title = intent.getStringExtra("NOTE_TITLE")
        val body = intent.getStringExtra("NOTE_BODY")

        titleView.text = title
        bodyView.text = body
    }
}
