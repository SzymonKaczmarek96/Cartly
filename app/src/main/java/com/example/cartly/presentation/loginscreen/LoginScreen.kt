package com.example.cartly.presentation.loginscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.ui.theme.AppTheme
import com.example.cartly.R

@Composable
fun LoginScreen() {
    LoginContentScreen()
}

@Composable
private fun LoginContentScreen() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background)
                .clip(MaterialTheme.shapes.extraSmall)

        ) {
            Icon(
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary),
                painter = painterResource(R.drawable.cartly_icon),
                contentDescription = "Cartly Icon",
            )
        }
        Text(
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            text = "Carlty"
        )

    }
}


@Preview
@Composable
private fun LoginContentScreenPreview() {
    AppTheme(
        content = { LoginContentScreen() }
    )
}

