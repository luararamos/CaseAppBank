package com.example.itautransferapp.data.local

import android.content.Context
import com.example.itautransferapp.common.CryptoManager
import com.example.itautransferapp.common.constants.ItauTransferConstants
import com.example.itautransferapp.data.remote.model.UserItem
import com.google.gson.Gson

object PreferencesManager {
    private val gson = Gson()
    fun saveLastLoggedUser(context: Context, user: UserItem) {
        val sharedPreferences = context.getSharedPreferences(ItauTransferConstants.PREFERENCES.USER_PREFS, Context.MODE_PRIVATE)
        val userJson = gson.toJson(user)
        val encryptedData = CryptoManager.encrypt(userJson)
        sharedPreferences.edit().putString(ItauTransferConstants.PREFERENCES.LAST_LOGGED_USER, encryptedData).apply()
    }

    fun getLastLoggedUser(context: Context): UserItem? {
        val sharedPreferences = context.getSharedPreferences(ItauTransferConstants.PREFERENCES.USER_PREFS, Context.MODE_PRIVATE)
        val encryptedData = sharedPreferences.getString(ItauTransferConstants.PREFERENCES.LAST_LOGGED_USER, null)
        return if (encryptedData != null) {
            val userJson = CryptoManager.decrypt(encryptedData)
            gson.fromJson(userJson, UserItem::class.java)
        } else null
    }
}