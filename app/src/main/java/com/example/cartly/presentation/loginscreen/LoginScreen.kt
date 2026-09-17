package com.example.cartly.presentation.loginscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsIgnoringVisibility
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.ui.theme.AppTheme
import com.example.cartly.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LoginScreen() {
    Scaffold(
//        contentWindowInsets = WindowInsets.systemBarsIgnoringVisibility,
        topBar = {LoginTopBarContent(
            modifier = Modifier
        )
                 },
        content = { paddingValues ->
            LoginContentScreen(paddingValues)
        }
    )

}

@Composable
private fun LoginContentScreen(paddingValues: PaddingValues){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(paddingValues)

    ) { }

}

@Composable
private fun LoginTopBarContent(
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clip(MaterialTheme.shapes.extraSmall)
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(32.dp)
                        .background(MaterialTheme.colorScheme.primary),
                    painter = painterResource(R.drawable.cartly_icon),
                    contentDescription = "Cartly Icon",
                )
            }
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Text(
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                text = "Carlty"
            )
        }
        Text("Logowanie")
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .clip(MaterialTheme.shapes.extraSmall)

            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .fillMaxSize(1f)
                        .background(MaterialTheme.colorScheme.primary)
                        .testTag("AccountIcon"),
                    contentDescription = "Cartly Icon",
                )
            }
        }
    }


@Preview
@Composable
private fun LoginTopBarContentPreview() {
    AppTheme(
        content = { LoginTopBarContent(
            modifier = Modifier
                .fillMaxWidth()
        ) }
    )
}

@Preview
@Composable
private fun LoginScreenPreview(){
    AppTheme(
        content = {
            LoginScreen()
        }
    )
}

