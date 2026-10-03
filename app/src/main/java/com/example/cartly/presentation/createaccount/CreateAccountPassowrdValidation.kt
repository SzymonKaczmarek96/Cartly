package com.example.cartly.presentation.createaccount

import javax.inject.Inject


internal class CreateAccountPasswordValidation @Inject constructor(){
    fun validatePassword(password: String): StrengthPassword {
       return when {
            password.length <= 9 ->  StrengthPassword.WeakPassword
            password.length <= 12 ->  StrengthPassword.MediumPassword
            password.length <= 15 ->  StrengthPassword.StrongPassword
           else ->  StrengthPassword.VeryStrongPassword
       }
    }
}