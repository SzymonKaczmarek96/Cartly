package com.example.cartly.presentation.createaccount

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

//savedStateHandle
// death process

@HiltViewModel
internal class CreateAccountViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val createAccountPasswordValidation: CreateAccountPasswordValidation
) : ViewModel() {
    private val _state = MutableStateFlow(
        savedStateHandle.get<CreateAccountState>("initial_state") ?: CreateAccountState()
    )
    val state: StateFlow<CreateAccountState> = _state.asStateFlow()

    fun changeFirstNameAndLastName(personalData: String) {
        val trimmed = personalData.trim()
        val parts = trimmed.split("\\s+".toRegex(), limit = 2)

        val firstName = parts.getOrNull(0)?.lowercase() ?: ""
        val lastName = parts.getOrNull(1)?.lowercase() ?: ""

        _state.update { currentState ->
            currentState.copy(
                personalData = PersonalData(
                    rawInput = personalData,
                    firstName = firstName,
                    lastName = lastName
                )
            )
        }
    }

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

@kotlinx.parcelize.Parcelize
sealed class StrengthPassword : android.os.Parcelable {
    @kotlinx.parcelize.Parcelize
    data object EmptyPassword : StrengthPassword()
    @kotlinx.parcelize.Parcelize
    data object WeakPassword : StrengthPassword()
    @kotlinx.parcelize.Parcelize
    data object MediumPassword : StrengthPassword()
    @kotlinx.parcelize.Parcelize
    data object StrongPassword : StrengthPassword()
    @kotlinx.parcelize.Parcelize
    data object VeryStrongPassword : StrengthPassword()
}