package com.example.itautransferapp.data.remote

import android.content.Context
import com.example.itautransferapp.data.local.PreferencesManager
import com.example.itautransferapp.data.remote.model.User
import com.example.itautransferapp.data.remote.model.UserAccount
import com.example.itautransferapp.domain.APIListener
import com.example.itautransferapp.domain.repository.UserRepository


class UserRepositoryImpl(context: Context) : BaseRepository(context), UserRepository {
    private val remote = RetrofitClient.getService(UserAccountService::class.java)

    val user = PreferencesManager.getLastLoggedUser(context)
    override fun getUser(listener: APIListener<User>) {
        executeCall(remote.getUserAccount(), listener)
    }

    override fun getUserAccount(id: String, listener: APIListener<UserAccount>) {
        executeCall(remote.getUserAccountById(id), listener)
    }
}