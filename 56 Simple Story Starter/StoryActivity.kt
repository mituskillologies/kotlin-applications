package com.example.storystarter1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.storystarter1.ui.theme.Storystarter1Theme

class StoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val heroName = intent.getStringExtra("HERO_NAME") ?: "A nameless hero"
        val location = intent.getStringExtra("LOCATION") ?: "a mysterious place"

        val storyBeginnings = listOf(
            "The grand adventure of $heroName began one sunny morning in the bustling city of $location...",
            "In a land of myth and a time of magic, the destiny of a great kingdom rested on the shoulders of one person, $heroName, who lived in the quiet village of $location.",
            "It was a dark and stormy night in $location. Our hero, $heroName, was about to uncover a terrible secret...",
            "The year is 3030. The flying cars of $location zoom past the window of $heroName, a private detective on the verge of a breakthrough case.",
            "Deep in the enchanted forest of $location, a young adventurer named $heroName was about to stumble upon an ancient mystery."
        )

        val storyText = storyBeginnings.random()

        setContent {
            Storystarter1Theme {
                val scrollState = rememberScrollState()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(scrollState),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = storyText)
                }
            }
        }
    }
}
