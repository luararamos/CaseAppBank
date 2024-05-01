package com.example.itautransferapp.common

object ValidationUtils {
    fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.endsWith(".com")
    }

    fun isValidPassword(password: String): Boolean {
        return password.any { it.isDigit() } && password.any { it.isUpperCase() }
    }
}