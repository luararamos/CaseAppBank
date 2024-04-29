package com.example.itautransferapp.presentation.screens.login

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itautransferapp.data.local.PreferencesManager
import com.example.itautransferapp.data.remote.model.UserAccount
import com.example.itautransferapp.domain.APIListener
import com.example.itautransferapp.domain.repository.UserAccountRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel (
    private val applicationContext: Context,
    private val userAccountRepository: UserAccountRepository
) : ViewModel() {
    private var _state = mutableStateOf("")
    val state: State<String> = _state

    private var _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private var _errorMessage = mutableStateOf("")
    val errorMessage: State<String> = _errorMessage


    fun loadUserData(email: String, password: String) {
        userAccountRepository.getUserAccount(object : APIListener<UserAccount> {
            override fun onSuccess(response: UserAccount) {
                val user = response.find {
                    it.email.trim() == email.trim()
                            && it.password.trim() == password.trim() }
                if (user != null) {
                    _state.value = user.id
                    PreferencesManager.saveLastLoggedUser(applicationContext, user)
                } else {
                    _errorMessage.value = "Invalid email or password"
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
}