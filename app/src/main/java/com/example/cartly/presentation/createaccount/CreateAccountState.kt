package com.example.cartly.presentation.createaccount

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class CreateAccountState(
    val submissionStatus: SubmissionStatus = SubmissionStatus.Idle,
    val personalData: PersonalData = PersonalData(),
    val email: String = "",
    val password: String ="",
    val passwordMatch: Boolean = false,
    val passwordStrength: StrengthPassword = StrengthPassword.EmptyPassword
) : Parcelable

@Parcelize
internal data class PersonalData(
    val rawInput: String = "",
    val firstName: String = "",
    val lastName: String = "",
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