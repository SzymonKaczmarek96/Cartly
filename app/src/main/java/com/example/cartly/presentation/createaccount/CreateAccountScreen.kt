@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.cartly.presentation.createaccount

import android.R.attr.navigationIcon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.app.ui.theme.AppTheme
import com.example.app.ui.theme.OnBackgroundLight
import com.example.cartly.R

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateAccountScreen() {
    Scaffold(
        topBar = {
            CreateAccountTopBar(modifier = Modifier)
        },
        content = { paddingValues ->
            CreateAccountContentScreen(
                modifier = Modifier
                    .padding(horizontal = 4.dp),
                paddingValues = paddingValues
            )
        },
        containerColor = MaterialTheme.colorScheme.secondaryContainer
    )
}

@Composable
private fun CreateAccountContentScreen(
    modifier: Modifier,
    paddingValues: PaddingValues) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)

    ) { }

}

@Composable
private fun CreateAccountTopBar(
    modifier: Modifier
){
    TopAppBar(
        modifier = modifier,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.shapes.extraSmall
                        ),
                    painter = painterResource(R.drawable.cartly_icon),
                    contentDescription = "Cartly Icon",
                )

                Text(
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                    text = "Cartly"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        navigationIcon = {
            Row(
                modifier,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    modifier = modifier,
                    onClick = {},
                    content = {
                        Icon(
                            modifier = Modifier
                                .size(32.dp),
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "onBackClick"
                        )
                    }
                )
            }
        },
        actions = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    text = "Create Account"
                )
                Surface(
                    modifier = Modifier
                        .size(32.dp),
                    shape = MaterialTheme.shapes.large,
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .fillMaxSize(1f)
                            .testTag("AccountIcon"),
                        contentDescription = "Cartly Icon",
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun CreateAccountTopBarPreview(){
    CreateAccountTopBar(
        Modifier.background(MaterialTheme.colorScheme.secondaryContainer)
    )
}

@Preview
@Composable
private fun CreateAccountScreenPreview() {
    AppTheme(
        content = {
            CreateAccountScreen()
        }
    )
}