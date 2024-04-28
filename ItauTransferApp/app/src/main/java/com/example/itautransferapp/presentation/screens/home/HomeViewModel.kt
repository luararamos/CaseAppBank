package com.example.itautransferapp.presentation.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.itautransferapp.data.model.UserAccount
import com.example.itautransferapp.domain.APIListener
import com.example.itautransferapp.domain.repository.UserAccountRepository

class HomeViewModel(
    private val userAccountRepository: UserAccountRepository
) : ViewModel() {
    private var _state = mutableStateOf("Initial Value")
    val state: State<String> get() = _state


    fun userData() {
        userAccountRepository.getUserAccount(object : APIListener<UserAccount> {
            override fun onSuccess(response: UserAccount) {
                _state.value = response.first().name
            }

            override fun onError(response: String) {

            }

            override fun onLoading(stateLoading: Boolean) {
            }
        })

    }
}