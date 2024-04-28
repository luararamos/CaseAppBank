package com.example.itautransferapp.domain.repository

import com.example.itautransferapp.data.model.UserAccount
import com.example.itautransferapp.domain.APIListener

interface UserAccountRepository {
    fun getUserAccount(listener: APIListener<UserAccount>)
}