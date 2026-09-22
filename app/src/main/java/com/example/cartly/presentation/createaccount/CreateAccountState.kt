package com.example.cartly.presentation.createaccount

internal data class CreateAccountState(
    val submissionStatus: SubmissionStatus = SubmissionStatus.Idle,
    val name: String = "",
    val email: String = "",
    val password: String ="",
)


internal sealed interface SubmissionStatus{

    data object Idle: SubmissionStatus

    data object Loading: SubmissionStatus

    data object Success: SubmissionStatus

    data class Error(val message: String): SubmissionStatus

}