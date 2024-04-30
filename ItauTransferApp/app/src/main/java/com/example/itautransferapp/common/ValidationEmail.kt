package com.example.itautransferapp.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ValidationEmail() : ViewModel() {


    val email = MutableLiveData<String>("")
    val password = MutableLiveData<String>("")
    val emailValid = MutableLiveData<Boolean>(true)
    val passwordValid = MutableLiveData<Boolean>(true)
    val isLoading = MutableLiveData<Boolean>(false)
    val emailErrorMessage = MutableLiveData<String>("")
    val passwordErrorMessage = MutableLiveData<String>("")
    val loginSuccess = MutableLiveData<Boolean>(false)

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