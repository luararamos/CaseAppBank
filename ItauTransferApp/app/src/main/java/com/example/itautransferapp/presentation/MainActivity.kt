package com.example.itautransferapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import com.example.itautransferapp.presentation.screens.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
        }
}

@Composable
fun MyComposeScreen() {
    MaterialTheme {
        Text("Hello, Compose!")
    }
}