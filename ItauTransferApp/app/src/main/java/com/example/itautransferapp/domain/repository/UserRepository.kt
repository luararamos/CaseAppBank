package com.example.itautransferapp.domain.repository

import com.example.itautransferapp.data.remote.model.User
import com.example.itautransferapp.data.remote.model.UserAccount
import com.example.itautransferapp.domain.APIListener

interface UserRepository {
    fun getUser(listener: APIListener<User>)

    fun getUserAccount(id:String ,listener: APIListener<UserAccount>)
}