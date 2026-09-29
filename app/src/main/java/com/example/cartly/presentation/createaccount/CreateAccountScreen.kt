@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.cartly.presentation.createaccount

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app.ui.theme.AppTheme
import com.example.cartly.R
import com.example.cartly.presentation.core.InputField
import com.example.cartly.presentation.core.LabeledDivider
import com.example.cartly.presentation.core.StrengthPasswordLabel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateAccountScreen() {
    Scaffold(
        topBar = {
            CreateAccountTopBar(modifier = Modifier)
        },
        content = { paddingValues ->
            CreateAccountContentScreen(
                modifier = Modifier.padding(horizontal = 4.dp),
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
    var confirmPassword by remember { mutableStateOf("") }

    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(36.dp))

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
                contentDescription = "Google Icon",
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
            value = name,
            onValueChange = { name = it },
            label = "Imię i nazwisko",
            placeholder = "Jan Kowalski",
            leadingIcon = Icons.Outlined.Person,
            containerColor = MaterialTheme.colorScheme.surface,
            errorText = "Imię i nazwisko jest wymagane",
            onNext = { focusManager.moveFocus(FocusDirection.Down) },
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
            onNext = { focusManager.moveFocus(FocusDirection.Down) },
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
            onNext = { focusManager.moveFocus(FocusDirection.Down) },
            imeAction = ImeAction.Next
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Siła hasła",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }

        StrengthPasswordLabel(
            modifier = Modifier.padding(vertical = 12.dp),
            strengthPassword = StrengthPassword.VeryStrongPassword
        )

        Text(
            fontSize = 14.sp,
            text = "Użyj min. 8 znaków, cyfry i znaku specjalnego",
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )

        Spacer(modifier = Modifier.height(16.dp))

        InputField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = "Powtórz hasło",
            placeholder = "Minimum 8 znaków",
            leadingIcon = Icons.Outlined.Lock,
            trailingIcon = if (isConfirmPasswordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility,
            onTrailingIconClick = { isConfirmPasswordVisible = !isConfirmPasswordVisible },
            visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            containerColor = MaterialTheme.colorScheme.surface,
            errorText = "Hasła muszą być identyczne",
            onDone = { focusManager.clearFocus() },
            imeAction = ImeAction.Done
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(
                    MaterialTheme.colorScheme.primary,
                    MaterialTheme.shapes.large
                )
                .clickable(
                    onClick = {},
                    enabled = true
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.surface,
                text = "Zarejestruj się i rozpocznij"
            )
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .padding(4.dp),
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Zarejestruj się",
                tint = MaterialTheme.colorScheme.surface
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                text = "Masz już konto?"
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                text = "Zaloguj się"
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun CreateAccountTopBar(
    modifier: Modifier
) {
    TopAppBar(
        modifier = modifier.shadow(elevation = 4.dp),
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
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = {},
                    content = {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Wróć"
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
                    modifier = Modifier.size(32.dp),
                    shape = MaterialTheme.shapes.large,
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .fillMaxSize()
                            .testTag("AccountIcon"),
                        contentDescription = "Account Icon",
                    )
                }
            }
        }
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
    HorizontalDivider()
}
