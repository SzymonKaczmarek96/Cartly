package com.example.cartly.presentation.createaccount

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

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

    fun validateEmptyFields() {
        if (state.value.personalData.rawInput.isEmpty()) {
            updatePersonalDataState(isError = true, errorMessage = "Imię i nazwisko jest wymagane")
        } else {
            updatePersonalDataState(isError = false)
        }
    }

    fun validateEmptyEmailField() {
        if (state.value.emailData.rawInput.isEmpty()) {
            updateEmailState(isError = true, errorMessage = "Adres e-mail jest wymagany")
        }
    }

    fun changeFirstNameAndLastName(personalData: String) {
        val trimmed = personalData.trim()
        val parts = trimmed.split("\\s+".toRegex(), limit = 2)

        val firstName = parts.getOrNull(0)?.lowercase() ?: ""
        val lastName = parts.getOrNull(1)?.lowercase() ?: ""

        updateState { currentState ->
            currentState.copy(
                personalData = currentState.personalData.copy(
                    rawInput = personalData,
                    firstName = firstName,
                    lastName = lastName
                )
            )
        }
    }

    fun validatePassword(password: String) {
        val passwordRegex = Regex(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?]).{8,}$"
        )
        when {
            password.length < 8 -> {
                updatePasswordStateWithError("Hasło jest za krótkie", password)
            }

            !passwordRegex.matches(password.trim()) -> {
                updatePasswordStateWithError("Hasło nie spełnia wymagań", password)
            }

            else -> {
                updatePasswordStateWithSuccess(password)
            }
        }
    }

    fun validateEmail(email: String) {
        val emailRegex = Regex(
            "^[A-Za-z0-9+._%\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,}$"
        )
        if (emailRegex.matches(email.trim())) {
            updateEmailState(rawInput = email, email = email, isError = false)
        } else {
            updateEmailState(
                rawInput = email,
                isError = true,
                errorMessage = "Adres e-mail jest nieprawidłowy"
            )
        }
    }

    fun validateRepeatPassword(repeatPassword: String) {
        if (repeatPassword == state.value.passwordData.password && repeatPassword.isNotEmpty()) {
            updateRepeatPasswordState(
                repeatPassword = repeatPassword,
                passwordMatch = true,
                isError = false
            )
        } else {
            val errorMessage =
                if (repeatPassword.isEmpty()) "To pole nie może być puste" else "Hasła nie są identyczne"
            updateRepeatPasswordState(
                repeatPassword = repeatPassword,
                passwordMatch = false,
                isError = true,
                errorMessage = errorMessage
            )
        }
    }

    fun checkIsFromValid(currentState: CreateAccountState): Boolean {
        return currentState.passwordData.passwordStrength != StrengthPassword.EmptyPassword
                && currentState.repeatPasswordData.passwordMatch
                && !currentState.emailData.isError
                && currentState.emailData.email.isNotEmpty()
                && currentState.personalData.rawInput.isNotEmpty()
                && currentState.personalData.rawInput.isNotBlank()
                && currentState.personalData.rawInput.length > 3
                && !currentState.passwordData.isError

    }

    private fun updatePersonalDataState(
        isError: Boolean,
        errorMessage: String = ""
    ) {
        updateState { currentState ->
            currentState.copy(
                personalData = currentState.personalData.copy(
                    isError = isError,
                    personalDataError = errorMessage
                )
            )
        }
    }

    private fun updateEmailState(
        rawInput: String? = null,
        email: String? = null,
        isError: Boolean,
        errorMessage: String = ""
    ) {
        updateState { currentState ->
            val currentEmailData = currentState.emailData
            currentState.copy(
                emailData = currentEmailData.copy(
                    rawInput = rawInput ?: currentEmailData.rawInput,
                    email = email ?: currentEmailData.email,
                    isError = isError,
                    emailError = errorMessage
                )
            )
        }
    }

    private fun updateRepeatPasswordState(
        repeatPassword: String,
        passwordMatch: Boolean,
        isError: Boolean,
        errorMessage: String = ""
    ) {
        updateState { currentState ->
            currentState.copy(
                repeatPasswordData = RepeatPasswordData(
                    repeatedPassword = repeatPassword,
                    passwordMatch = passwordMatch,
                    isError = isError,
                    repeatedPasswordError = errorMessage
                )
            )
        }
    }

    private fun updatePasswordStateWithError(
        errorMessage: String,
        password: String
    ) {
        val passwordData = state.value.passwordData
        updateState { currentState ->
            currentState.copy(
                passwordData = passwordData.copy(
                    rawInput = password,
                    password = password,
                    isError = true,
                    passwordError = errorMessage
                )
            )
        }
    }

    private fun updatePasswordStateWithSuccess(password: String) {
        val passwordStrength = createAccountPasswordValidation.validatePassword(password)
        val passwordData = state.value.passwordData
        updateState { currentState ->
            currentState.copy(
                passwordData = passwordData.copy(
                    rawInput = password,
                    isError = false,
                    passwordError = "",
                    password = password,
                    passwordStrength = passwordStrength
                )
            )
        }
    }

    private fun isLoading() {
        updateState {
            it.copy(submissionStatus = SubmissionStatus.Loading)
        }
    }

    private fun updateState(transform: (CreateAccountState) -> CreateAccountState) {
        val currentState = state.value
        val newState = transform(currentState)
        val finalState = newState.copy(
            isFormValid = checkIsFromValid(newState)
        )
        savedStateHandle[stateKey] = finalState
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