package com.example.itautransferapp.presentation.screens.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ValidationViewModel() : ViewModel() {


    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val emailValid = MutableLiveData<Boolean>(true)
    val passwordValid = MutableLiveData<Boolean>(true)
    val isLoading = MutableLiveData<Boolean>(false)
    val emailErrorMessage = MutableLiveData<String>("")
    val passwordErrorMessage = MutableLiveData<String>("")
    val loginSuccess = MutableLiveData<Boolean>(false)

    fun onEmailChanged(email: String) {
        this.email.value = email
        emailValid.value = isValidEmail(email)
        emailErrorMessage.value =
            if (emailValid.value == true) "" else "Email deve conter '@' e terminar com '.com'"
    }

    fun onPasswordChanged(password: String) {
        this.password.value = password
        passwordValid.value = isValidPassword(password)
        passwordErrorMessage.value =
            if (passwordValid.value == true) "" else "Senha deve conter pelo menos um número e uma letra maiúscula"
    }


    private fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.endsWith(".com")
    }


    private fun isValidPassword(password: String): Boolean {
        return password.any { it.isDigit() } && password.any { it.isUpperCase() }
    }

    fun login() {
        isLoading.value = true
        if (emailValid.value == true && passwordValid.value == true) {
            viewModelScope.launch {
                delay(2000)
                isLoading.value = false
                loginSuccess.value = true
            }
        } else {
            isLoading.value = false
        }
    }
}