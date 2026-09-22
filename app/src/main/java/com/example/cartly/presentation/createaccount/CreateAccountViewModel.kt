package com.example.cartly.presentation.createaccount

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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