package com.example.cartly.presentation.createaccount

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

//savedStateHandle
// death process

@HiltViewModel
internal class CreateAccountViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val createAccountPasswordValidation: CreateAccountPasswordValidation
) : ViewModel() {
    private val stateKey = "account_state"

    val state = savedStateHandle.getStateFlow(
        key = stateKey,
        initialValue = CreateAccountState()
    )

    fun changeFirstNameAndLastName(personalData: String) {
        val trimmed = personalData.trim()
        val parts = trimmed.split("\\s+".toRegex(), limit = 2)

        val firstName = parts.getOrNull(0)?.lowercase() ?: ""
        val lastName = parts.getOrNull(1)?.lowercase() ?: ""

        updateState { currentState ->
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
            updateState { currentState ->
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
            updateState { currentState ->
                currentState.copy(email = email)
            }
        }
    }

    fun validateRepeatPassword(repeatPassword: String) {
        if (repeatPassword == state.value.password) {
            updateState { currentState ->
                currentState.copy(passwordMatch = true)
            }
        }
    }

    fun sumUpValidation(): Boolean {
        return state.value.passwordStrength != StrengthPassword.EmptyPassword
                && state.value.passwordMatch
                && state.value.email.isNotEmpty()
    }

    private fun isLoading() {
        updateState {
            it.copy(submissionStatus = SubmissionStatus.Loading)
        }
    }

    private fun updateState(transform: (CreateAccountState) -> CreateAccountState) {
        val currentState = state.value
        savedStateHandle[stateKey] = transform(currentState)
    }
}

@Parcelize
sealed class StrengthPassword : Parcelable {
    @Parcelize
    data object EmptyPassword : StrengthPassword()
    @Parcelize
    data object WeakPassword : StrengthPassword()
    @Parcelize
    data object MediumPassword : StrengthPassword()
    @Parcelize
    data object StrongPassword : StrengthPassword()
    @Parcelize
    data object VeryStrongPassword : StrengthPassword()
}