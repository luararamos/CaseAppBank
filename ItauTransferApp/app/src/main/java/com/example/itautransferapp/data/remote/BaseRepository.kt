package com.example.itautransferapp.data.remote

import android.content.Context
import com.example.itautransferapp.R
import com.example.itautransferapp.common.constants.ItauTransferConstants
import com.example.itautransferapp.domain.APIListener
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository(val context: Context) {

    private fun failResponse(jsonErrorBody: String): String {
        return try {
            val errorResponse = Gson().fromJson(jsonErrorBody, ErrorResponse::class.java)
            errorResponse.error
        } catch (e: JsonSyntaxException) {
            "Erro de análise JSON: ${e.message}"  // Mensagem de erro padrão para erros de parsing
        }
    }

    fun <T> executeCall(call: Call<T>, listener: APIListener<T>) {
        listener.onLoading(true)
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.code() == ItauTransferConstants.HTTP.SUCCESS) {
                    response.body()?.let { listener.onSuccess(it) }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Erro desconhecido"
                    listener.onError(failResponse(errorBody))
                }
                listener.onLoading(false)
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                listener.onError("${ItauTransferConstants.ERROR.ERROR_UNEXPECTED}: ${t.message}")
                listener.onLoading(false)
            }
        })
    }


}