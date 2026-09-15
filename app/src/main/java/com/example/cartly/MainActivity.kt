package com.example.cartly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.app.ui.theme.AppTheme
import com.example.cartly.presentation.loginscreen.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme(
                darkTheme = false,
                content = { LoginScreen() }
            )
        }
    }
}

