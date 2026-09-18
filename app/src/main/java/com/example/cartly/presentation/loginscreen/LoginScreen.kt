@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.cartly.presentation.loginscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
        modifier = Modifier
            .padding(horizontal = 4.dp),
        topBar = {
            LoginTopBarContent(modifier = Modifier
                .padding(horizontal = 4.dp))
        },
        content = { paddingValues ->
            LoginContentScreen(
                modifier = Modifier
                    .padding(horizontal = 4.dp),
                paddingValues = paddingValues
            )
        },
        containerColor = MaterialTheme.colorScheme.secondaryContainer
    )
}

@Composable
private fun LoginContentScreen(
    modifier: Modifier,
    paddingValues: PaddingValues) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)

    ) { }

}

@Composable
private fun LoginTopBarContent(
    modifier: Modifier
){
    CenterAlignedTopAppBar(
        modifier = modifier,
        title ={ Text("Login") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        navigationIcon = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(32.dp)
                        .background(MaterialTheme.colorScheme.primary),
                    painter = painterResource(R.drawable.cartly_icon),
                    contentDescription = "Cartly Icon",
                )

                Text(
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    text = "Carlty"
                )
            }
        },
        actions = {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(MaterialTheme.colorScheme.secondaryContainer,
                        shape = MaterialTheme.shapes.large
                    )
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
    )
}

@Preview
@Composable
private fun LoginTopBarContentPreview(){
    LoginTopBarContent(
        Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
    )
}

@Preview
@Composable
private fun LoginScreenPreview() {
    AppTheme(
        content = {
            LoginScreen()
        }
    )
}