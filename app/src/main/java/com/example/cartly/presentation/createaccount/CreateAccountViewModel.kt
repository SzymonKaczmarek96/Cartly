package com.example.cartly.presentation.createaccount

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class CreateAccountViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val initState: CreateAccountState = CreateAccountState(),
): ViewModel() {
    private val _state = MutableStateFlow(initState)
    val state: StateFlow<CreateAccountState> = _state.asStateFlow()

    //TODO
}

sealed class StrengthPassword(){
    data object EmptyPassword: StrengthPassword()
    data object WeakPassword: StrengthPassword()
    data object MediumPassword: StrengthPassword()
    data object StrongPassword: StrengthPassword()
    data object VeryStrongPassword: StrengthPassword()
}