package com.example.itautransferapp.domain.repository

import com.example.itautransferapp.data.remote.model.UserAccount
import com.example.itautransferapp.domain.APIListener

interface UserAccountRepository {
    fun getUserAccount(listener: APIListener<UserAccount>)
}