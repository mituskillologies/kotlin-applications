package com.example.simplenotetaker

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NoteInputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_input)

        val titleEditText: EditText = findViewById(R.id.edit_text_title)
        val bodyEditText: EditText = findViewById(R.id.edit_text_body)
        val saveButton: Button = findViewById(R.id.button_save_note)

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val body = bodyEditText.text.toString().trim()

            if (title.isEmpty() || body.isEmpty()) {
                Toast.makeText(this, "Title and note cannot be empty.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, NoteDisplayActivity::class.java)
                intent.putExtra("NOTE_TITLE", title)
                intent.putExtra("NOTE_BODY", body)
                startActivity(intent)
            }
        }
    }
}
