package com.example.itautransferapp.data.remote

import android.content.Context
import com.example.itautransferapp.data.remote.model.UserAccount
import com.example.itautransferapp.domain.APIListener
import com.example.itautransferapp.domain.repository.UserAccountRepository


class UserAccountRepositoryImpl(context: Context) : BaseRepository(context), UserAccountRepository {
    private val remote = RetrofitClient.getService(UserAccountService::class.java)

    override fun getUserAccount(listener: APIListener<UserAccount>) {
        executeCall(remote.getUserAccount(), listener)
    }
}