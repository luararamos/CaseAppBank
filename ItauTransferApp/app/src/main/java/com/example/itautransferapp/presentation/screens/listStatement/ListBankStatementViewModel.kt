package com.example.itautransferapp.presentation.screens.listStatement

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.itautransferapp.common.details
import com.example.itautransferapp.data.local.PreferencesManager
import com.example.itautransferapp.data.remote.model.UserAccount
import com.example.itautransferapp.domain.APIListener
import com.example.itautransferapp.domain.repository.UserRepository
import com.example.itautransferapp.presentation.detailsview.BankStatementDetails

class ListBankStatementViewModel(
    private val applicationContext: Context,
    private val userRepository: UserRepository
) : ViewModel() {

    private val _stateList = mutableStateOf(ListState<BankStatementDetails>())
    val stateList: State<ListState<BankStatementDetails>> = _stateList

    fun getListBankStatement() {
        val user = PreferencesManager.getLastLoggedUser(applicationContext)
        if (user != null) {
            userRepository.getUserAccount(id = user.id, object : APIListener<UserAccount> {
                override fun onSuccess(response: UserAccount) {
                    if (response.isNotEmpty()) {
                        val accountItem = response[0]
                        val mappedStatements = accountItem.payments.details()
                        _stateList.value = stateList.value.copy(
                            value = mappedStatements
                        )

                    } else {
                        _stateList.value = stateList.value.copy(isLoading = false)
                    }
                }

                override fun onError(response: String) {
                    _stateList.value = stateList.value.copy(
                        error = response
                    )
                }

                override fun onLoading(stateLoading: Boolean) {
                    _stateList.value = stateList.value.copy(isLoading = stateLoading)
                }

            })

        }
    }
}