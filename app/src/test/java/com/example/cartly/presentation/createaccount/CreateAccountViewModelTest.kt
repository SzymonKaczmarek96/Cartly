package com.example.cartly.presentation.createaccount

import androidx.lifecycle.SavedStateHandle
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CreateAccountViewModelTest {

    private lateinit var viewModel: CreateAccountViewModel
    private lateinit var passwordValidation: CreateAccountPasswordValidation
    private lateinit var savedStateHandle: SavedStateHandle

    @Before
    fun setUp() {
        savedStateHandle = SavedStateHandle()
        passwordValidation = CreateAccountPasswordValidation()
        viewModel = CreateAccountViewModel(savedStateHandle, passwordValidation)
    }

    @Test
    fun `should initialize with default state and invalid form`() {
        val state = viewModel.state.value
        assertEquals("", state.personalData.rawInput)
        assertEquals("", state.emailData.rawInput)
        assertEquals("", state.passwordData.rawInput)
        assertFalse(state.isFormValid)
    }

    @Test
    fun `should split first and last name correctly when changeFirstNameAndLastName is called`() {
        viewModel.changeFirstNameAndLastName("Jan Kowalski")

        val state = viewModel.state.value
        assertEquals("Jan Kowalski", state.personalData.rawInput)
        assertEquals("jan", state.personalData.firstName)
        assertEquals("kowalski", state.personalData.lastName)
    }

    @Test
    fun `should set error when personalData rawInput is empty in validateEmptyFields`() {
        viewModel.validateEmptyFields()

        val state = viewModel.state.value
        assertTrue(state.personalData.isError)
        assertEquals("Imię i nazwisko jest wymagane", state.personalData.personalDataError)
    }

    @Test
    fun `should update emailData without error when email is valid`() {
        viewModel.validateEmail("jan@domain.com")

        val state = viewModel.state.value
        assertEquals("jan@domain.com", state.emailData.email)
        assertFalse(state.emailData.isError)
    }

    @Test
    fun `should set emailData error when email is invalid`() {
        viewModel.validateEmail("invalid-email")

        val state = viewModel.state.value
        assertTrue(state.emailData.isError)
        assertEquals("Adres e-mail jest nieprawidłowy", state.emailData.emailError)
    }

    @Test
    fun `should set emailData error when email field is empty in validateEmptyEmailField`() {
        viewModel.validateEmptyEmailField()

        val state = viewModel.state.value
        assertTrue(state.emailData.isError)
        assertEquals("Adres e-mail jest wymagany", state.emailData.emailError)
    }

    @Test
    fun `should update password strength and clear error when password is valid`() {
        viewModel.validatePassword("Secret123!")

        val state = viewModel.state.value
        assertEquals("Secret123!", state.passwordData.password)
        assertFalse(state.passwordData.isError)
        assertEquals(StrengthPassword.MediumPassword, state.passwordData.passwordStrength)
    }

    @Test
    fun `should set short password error when password length is less than 8`() {
        viewModel.validatePassword("Short1!")

        val state = viewModel.state.value
        assertTrue(state.passwordData.isError)
        assertEquals("Hasło jest za krótkie", state.passwordData.passwordError)
    }

    @Test
    fun `should set passwordMatch to true when repeat password matches`() {
        viewModel.validatePassword("Secret123!")
        viewModel.validateRepeatPassword("Secret123!")

        val state = viewModel.state.value
        assertTrue(state.repeatPasswordData.passwordMatch)
        assertFalse(state.repeatPasswordData.isError)
    }

    @Test
    fun `should set repeat password error when passwords do not match`() {
        viewModel.validatePassword("Secret123!")
        viewModel.validateRepeatPassword("Different123!")

        val state = viewModel.state.value
        assertFalse(state.repeatPasswordData.passwordMatch)
        assertTrue(state.repeatPasswordData.isError)
        assertEquals("Hasła nie są identyczne", state.repeatPasswordData.repeatedPasswordError)
    }

    @Test
    fun `should set isFormValid to true when all input fields are valid`() {
        viewModel.changeFirstNameAndLastName("Jan Kowalski")
        viewModel.validateEmail("jan@domain.com")
        viewModel.validatePassword("Secret123!")
        viewModel.validateRepeatPassword("Secret123!")

        val state = viewModel.state.value
        assertTrue(state.isFormValid)
    }
}
