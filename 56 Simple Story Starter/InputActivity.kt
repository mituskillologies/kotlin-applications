package com.example.storystarter1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.storystarter1.ui.theme.Storystarter1Theme

class InputActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Storystarter1Theme {
                InputScreen()
            }
        }
    }
}

@Composable
fun InputScreen() {
    var heroName by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = heroName,
            onValueChange = { heroName = it },
            label = { Text("Enter a hero's name...") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Enter a location...") },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )
        Button(
            onClick = {
                if (heroName.isNotBlank() && location.isNotBlank()) {
                    val intent = Intent(context, StoryActivity::class.java).apply {
                        putExtra("HERO_NAME", heroName)
                        putExtra("LOCATION", location)
                    }
                    context.startActivity(intent)
                } else {
                    Toast.makeText(context, "Please provide both a name and a location.", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Start the Story")
        }
    }
}
