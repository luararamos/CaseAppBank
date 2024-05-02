package com.example.itautransferapp.presentation.screens.login

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.itautransferapp.R
import com.example.itautransferapp.common.ValidationEmailAndPassword
import com.example.itautransferapp.data.local.PreferencesManager
import com.example.itautransferapp.data.remote.model.User
import com.example.itautransferapp.domain.APIListener
import com.example.itautransferapp.domain.repository.UserRepository

class LoginViewModel(
    private val applicationContext: Context,
    private val userRepository: UserRepository
) : ViewModel() {
    var email = mutableStateOf("")
    val password = MutableLiveData<String>("")
    val emailValid = MutableLiveData<Boolean>(true)
    val passwordValid = MutableLiveData<Boolean>(true)
    val emailErrorMessage = MutableLiveData<String>("")
    val passwordErrorMessage = MutableLiveData<String>("")


    private var _state = mutableStateOf("")
    val state: State<String> = _state

    private var _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private var _errorMessage = mutableStateOf("")
    val errorMessage: State<String> = _errorMessage

    init {
        showSavedUserIfAvailable()
    }

    fun loadUserData(email: String, password: String) {
        userRepository.getUser(object : APIListener<User> {
            override fun onSuccess(response: User) {
                val user = response.find {
                    it.email.trim() == email.trim()
                            && it.password.trim() == password.trim()
                }
                if (user != null) {
                    _state.value = user.id
                    PreferencesManager.saveLastLoggedUser(applicationContext, user)
                } else {
                    _errorMessage.value =
                        applicationContext.getString(R.string.email_and_password_invalid)
                }
                _isLoading.value = false
            }

            override fun onError(response: String) {
                _isLoading.value = false
                _errorMessage.value = response
            }

            override fun onLoading(stateLoading: Boolean) {
                _isLoading.value = stateLoading
            }
        })
    }

    fun showSavedUserIfAvailable() {
        val user = PreferencesManager.getLastLoggedUser(applicationContext)
        if (user != null) {
            this.email.value = user.email
        } else {
            this.email.value = ""
        }
    }

    fun onEmailChanged(email: String) {
        this.email.value = email
        emailValid.value = ValidationEmailAndPassword.isValidEmail(email)
        emailErrorMessage.value =
            if (emailValid.value == true) "" else applicationContext.getString(R.string.email_error)
    }

    fun onPasswordChanged(password: String) {
        this.password.value = password
        passwordValid.value = ValidationEmailAndPassword.isValidPassword(password)
        passwordErrorMessage.value =
            if (passwordValid.value == true) "" else applicationContext.getString(R.string.password_error)
    }
}