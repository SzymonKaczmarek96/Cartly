package com.example.cartly.presentation.createaccount

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class CreateAccountState(
    val submissionStatus: SubmissionStatus = SubmissionStatus.Idle,
    val personalData: PersonalData = PersonalData(),
    val emailData: EmailData = EmailData(),
    val passwordData: PasswordData = PasswordData(),
    val repeatPasswordData: RepeatPasswordData = RepeatPasswordData(),
    val isFormValid: Boolean = false,
) : Parcelable

@Parcelize
internal data class PersonalData(
    val rawInput: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val personalDataError: String = "",
    val isError: Boolean = false,
) : Parcelable

@Parcelize
internal data class EmailData(
    val rawInput: String = "",
    val email: String = "",
    val isError: Boolean = false,
    val emailError: String = ""
) : Parcelable

@Parcelize
internal data class PasswordData(
    val rawInput: String = "",
    val password: String = "",
    val isError: Boolean = false,
    val passwordError: String = "",
    val passwordStrength: StrengthPassword = StrengthPassword.EmptyPassword
) : Parcelable

@Parcelize
internal data class RepeatPasswordData(
    val repeatedPassword: String = "",
    val passwordMatch: Boolean = false,
    val isError: Boolean = false,
    val repeatedPasswordError: String = "",
) : Parcelable

internal sealed interface SubmissionStatus : Parcelable {

    @Parcelize
    data object Idle: SubmissionStatus

    @Parcelize
    data object Loading: SubmissionStatus

    @Parcelize
    data object Success: SubmissionStatus

    @Parcelize
    data class Error(val message: String): SubmissionStatus

}