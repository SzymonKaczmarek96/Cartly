package com.example.cartly.presentation.createaccount

import android.R.attr.password

object CreateAccountPasswordValidation {
    fun validatePassword(password: String): StrengthPassword {
       return when {
            password.length <= 9 ->  StrengthPassword.WeakPassword
            password.length <= 12 ->  StrengthPassword.MediumPassword
            password.length <= 15 ->  StrengthPassword.StrongPassword
           else ->  StrengthPassword.VeryStrongPassword
       }
    }
}