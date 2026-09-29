package com.example.cartly.presentation.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.app.ui.theme.ExcellentLight
import com.example.app.ui.theme.GoodLight
import com.example.app.ui.theme.WarningLight
import com.example.cartly.presentation.createaccount.StrengthPassword

@Composable
fun StrengthPasswordLabel(
    modifier: Modifier = Modifier,
    strengthPassword: StrengthPassword,
) {
    val (activeColor, repeat) = when (strengthPassword) {
        is StrengthPassword.EmptyPassword -> MaterialTheme.colorScheme.surface to 0
        is StrengthPassword.WeakPassword -> MaterialTheme.colorScheme.error to 1
        is StrengthPassword.MediumPassword -> WarningLight to 2
        is StrengthPassword.StrongPassword -> GoodLight to 3
        is StrengthPassword.VeryStrongPassword -> ExcellentLight to 4
    }

    val defaultColor = MaterialTheme.colorScheme.surface


    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(
            times = 4,
            action = { index ->
                HorizontalDivider(
                    modifier = Modifier
                        .weight(1f)
                        .clip(shape = MaterialTheme.shapes.medium),
                    thickness = 10.dp,
                    color = if (index < repeat) activeColor else defaultColor
                )
            }
        )
    }
}
