package com.example.cartly.presentation.loginscreen

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen(){
  RootView()
}

@Composable
private fun RootView(){
    LoginContentScreen()
}

@Composable
private fun LoginContentScreen(){
    Row(

    ){

    }
    Scaffold(
        content = TODO()
    )
}


@Preview
@Composable
private fun LoginContentScreenPreview(){
    LoginContentScreen()
}

