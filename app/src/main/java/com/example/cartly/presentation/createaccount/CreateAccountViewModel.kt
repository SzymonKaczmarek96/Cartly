package com.example.cartly.presentation.createaccount

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

//TODO Hilt
internal class CreateAccountViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val initState: CreateAccountState = CreateAccountState(),
    private val createAccountPasswordValidation: CreateAccountPasswordValidation
) : ViewModel() {
    private val _state = MutableStateFlow(initState)
    val state: StateFlow<CreateAccountState> = _state.asStateFlow()

    fun validatePassword(password: String) {
        val passwordRegex = Regex("^[a-zA-Z0-9!@#$%^&*()-+]{8,}$")
        if (passwordRegex.matches(password.trim())) {
            val passwordStrength = createAccountPasswordValidation.validatePassword(password)
            _state.update { currentState ->
                currentState.copy(
                    passwordStrength = passwordStrength,
                    password = password
                )
            }
        }
    }

    fun emailValidator(email: String) {
        val emailRegex = Regex(
            "^[A-Za-z0-9+._%\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,}$"
        )
        if (emailRegex.matches(email.trim())) {
            _state.update { currentState ->
                currentState.copy(email = email)
            }
        }
    }

    fun validateRepeatPassword(repeatPassword: String) {
        if (repeatPassword == _state.value.password) {
            _state.update { currentState ->
                currentState.copy(passwordMatch = true)
            }
        }
    }

    fun sumUpValidation(): Boolean {
        val state = _state.value
        return state.passwordStrength != StrengthPassword.EmptyPassword
                && state.passwordMatch
                && state.email.isNotEmpty()
    }

    private fun isLoading() {
        _state.update {
            it.copy(submissionStatus = SubmissionStatus.Loading)
        }
    }

}

sealed class StrengthPassword() {
    data object EmptyPassword : StrengthPassword()
    data object WeakPassword : StrengthPassword()
    data object MediumPassword : StrengthPassword()
    data object StrongPassword : StrengthPassword()
    data object VeryStrongPassword : StrengthPassword()
}