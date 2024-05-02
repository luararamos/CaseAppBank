package com.example.itautransferapp.data.remote

import android.content.Context
import com.example.itautransferapp.data.local.PreferencesManager
import com.example.itautransferapp.data.remote.model.UserAccountItem
import com.example.itautransferapp.data.remote.model.Users
import com.example.itautransferapp.domain.repository.TransferRepository

class TransferRepositoryImpl(context: Context) : BaseRepository(context), TransferRepository {
    private val remote = RetrofitClient.getService(TransferService::class.java)
    val user = PreferencesManager.getLastLoggedUser(context)

    override suspend fun checkAmount(transferValue: String): Boolean {
        if (user != null) {

            val valueInCents = transferValue.toIntOrNull() ?: return false
            val value = valueInCents / 100.0
            return try {
                val accountResponses = remote.getAccount(user.id.toInt())
                // Verifica se o saldo da conta é maior ou igual ao valor da transferência
                accountResponses.any {
                    it.amount.toDouble() >= value
                }
            } catch (e: Exception) {
                false
            }
        }
        return false
    }

    override suspend fun getAmount(): String {
        if (user != null) {
            val accountResponses = remote.getAccount(user.id.toInt())
            accountResponses.any {
                return it.amount
            }
        } else return ""

        return ""
    }

    override suspend fun getUsers(): List<Users> {
        return remote.getUsers()
    }

    override suspend fun getAccountResponses(id: Int): List<UserAccountItem> {
        return remote.getAccount(id)
    }

    override fun getUserId(): String {
        if (user != null) {
            return user.id
        }
        return ""
    }


}