@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.cartly.presentation.createaccount

import android.R.attr.inset
import android.R.attr.name
import android.R.attr.navigationIcon
import android.R.attr.password
import android.R.attr.text
import android.R.attr.thickness
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.app.ui.theme.AppTheme
import com.example.app.ui.theme.OnBackgroundLight
import com.example.cartly.R
import com.example.cartly.presentation.core.InputField
import com.example.cartly.presentation.core.LabeledDivider

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
    paddingValues: PaddingValues
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())

    ) {
        Spacer(Modifier.height(48.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(
                    MaterialTheme.colorScheme.surface,
                    MaterialTheme.shapes.large
                )
                .clickable(
                    onClick = {},
                    enabled = true
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .padding(8.dp),
                painter = painterResource(R.drawable.google_icon),
                contentDescription = "123",
                tint = Color.Unspecified
            )
            Text(
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                text = "Zarejestruj się przez Google"
            )
        }

        Spacer(Modifier.height(24.dp))

        LabeledDivider(
            text = "LUB WYPEŁNIJ FORMULARZ",
            color = MaterialTheme.colorScheme.secondary,
            thickness = 1.dp
        )

        Spacer(Modifier.height(24.dp))

        InputField(
            modifier = Modifier,
            value = name,
            onValueChange = { name = it },
            label = "Imię i nazwisko",
            placeholder = "Jan Kowalski",
            leadingIcon = Icons.Outlined.Person,
            containerColor = MaterialTheme.colorScheme.surface,
            errorText = "Imie i nazwisko jest wymagane",
            onNext = {focusManager.moveFocus(FocusDirection.Down)},
            imeAction = ImeAction.Next
        )

        Spacer(modifier = Modifier.height(24.dp))

        InputField(
            value = email,
            onValueChange = { email = it },
            label = "Adres e-mail",
            placeholder = "jan@twojadomena.pl",
            leadingIcon = Icons.Outlined.Email,
            containerColor = MaterialTheme.colorScheme.surface,
            errorText = "Adres email jest wymagany",
            onNext = {focusManager.moveFocus(FocusDirection.Down)},
            imeAction = ImeAction.Next
        )

        Spacer(modifier = Modifier.height(24.dp))

        InputField(
            value = password,
            onValueChange = { password = it },
            label = "Hasło",
            placeholder = "Minimum 8 znaków",
            leadingIcon = Icons.Outlined.Lock,
            trailingIcon = if (isPasswordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
            onTrailingIconClick = { isPasswordVisible = !isPasswordVisible },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            containerColor = MaterialTheme.colorScheme.surface,
            errorText = "Hasło jest wymagane",
            onDone = {focusManager.clearFocus()},
            imeAction = ImeAction.Done
        )

        //TODO Strength password
        //TODO Repeat password
        // TODO Accept regulations
        // TODO Do you have account? login ->

    }
}



@Composable
private fun CreateAccountTopBar(
    modifier: Modifier
) {
    TopAppBar(
        modifier = modifier
            .shadow(elevation = 4.dp),
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
private fun CreateAccountTopBarPreview() {
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

@Preview
@Composable
private fun DividerPreview() {
    Divider()
}