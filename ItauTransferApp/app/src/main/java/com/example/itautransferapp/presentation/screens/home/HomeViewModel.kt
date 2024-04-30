package com.example.itautransferapp.presentation.screens.home

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.itautransferapp.R
import com.example.itautransferapp.data.local.PreferencesManager
import com.example.itautransferapp.data.remote.model.UserAccount
import com.example.itautransferapp.domain.APIListener
import com.example.itautransferapp.domain.repository.UserRepository
import com.example.itautransferapp.presentation.data.UserDetails

class HomeViewModel(
    private val applicationContext: Context,
    private val userRepository: UserRepository
) : ViewModel() {

    private var _state = mutableStateOf(UserDetails("", "", "", "", "", ""))
    val state: State<UserDetails> = _state

    private var _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private var _errorMessage = mutableStateOf("")
    val errorMessage: State<String> = _errorMessage

    fun getUserData() {
        val user = PreferencesManager.getLastLoggedUser(applicationContext)
        if (user != null) {
            userRepository.getUserAccount(id = user.id, object : APIListener<UserAccount> {
                override fun onSuccess(response: UserAccount) {
                    if (response.isNotEmpty()) {
                        val accountItem = response[0]
                        _state.value = UserDetails(
                            id = user.id,
                            name = user.name,
                            ag = applicationContext.getString(R.string.number_ag_itau) ,
                            account = accountItem.account,
                            value = accountItem.amount,
                            photo = user.avatar
                        )
                    } else {
                        _state.value = UserDetails(
                            id = user.id,
                            name = user.name,
                            ag = applicationContext.getString(R.string.number_ag_itau),
                            account = "",
                            value = "",
                            photo= user.avatar)
                    }
                }

                override fun onError(response: String) {

                }

                override fun onLoading(stateLoading: Boolean) {

                }
            })
        }

    }


}