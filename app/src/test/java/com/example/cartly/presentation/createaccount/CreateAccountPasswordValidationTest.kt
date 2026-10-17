package com.example.cartly.presentation.createaccount

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CreateAccountPasswordValidationTest {

    private lateinit var passwordValidation: CreateAccountPasswordValidation

    @Before
    fun setUp() {
        passwordValidation = CreateAccountPasswordValidation()
    }

    @Test
    fun `should return WeakPassword when password length is 9 or less`() {
        val strength = passwordValidation.validatePassword("Pass123!")
        assertEquals(StrengthPassword.WeakPassword, strength)
    }

    @Test
    fun `should return MediumPassword when password length is between 10 and 12`() {
        val strength = passwordValidation.validatePassword("Pass123!456")
        assertEquals(StrengthPassword.MediumPassword, strength)
    }

    @Test
    fun `should return StrongPassword when password length is between 13 and 15`() {
        val strength = passwordValidation.validatePassword("Pass123!4567890")
        assertEquals(StrengthPassword.StrongPassword, strength)
    }

    @Test
    fun `should return VeryStrongPassword when password length is greater than 15`() {
        val strength = passwordValidation.validatePassword("Pass123!456789012")
        assertEquals(StrengthPassword.VeryStrongPassword, strength)
    }
}
