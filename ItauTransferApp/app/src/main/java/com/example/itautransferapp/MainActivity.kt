package com.example.itautransferapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeScreen()
        }
    }
}

@Composable
fun MyComposeScreen() {
    MaterialTheme {
        Text("Hello, Compose!")
    }
}