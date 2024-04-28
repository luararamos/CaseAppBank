package com.example.itautransferapp.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.itautransferapp.R
import com.example.itautransferapp.common.constants.ItauTransferConstants
import com.example.itautransferapp.domain.APIListener
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository(val context: Context) {

    private fun failResponse(str: String): String {
        return Gson().fromJson(str, String::class.java)
    }

    fun <T> executeCall(call: Call<T>, listener: APIListener<T>) {
        listener.onLoading(true)
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.code() == ItauTransferConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess(it) }
                } else {
                    listener.onError(failResponse(response.errorBody()!!.string()))
                }
                listener.onLoading(false)
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                listener.onError(context.getString(R.string.ERROR_UNEXPECTED))
                listener.onLoading(false)
            }
        })
    }


}