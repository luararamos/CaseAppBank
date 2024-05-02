package com.example.itautransferapp

import android.content.Context
import com.example.itautransferapp.common.constants.ItauTransferConstants
import com.example.itautransferapp.data.remote.BaseRepository
import com.example.itautransferapp.domain.APIListener
import okhttp3.MediaType.Companion.toMediaType
import org.junit.Test
import org.junit.Before
import org.mockito.Mockito.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import org.mockito.kotlin.anyOrNull

class BaseRepositoryTest {

    lateinit var repository: BaseRepository
    lateinit var context: Context
    lateinit var apiListener: APIListener<Any>
    lateinit var call: Call<Any>

    @Before
    fun setUp() {
        context = mock(Context::class.java)
        repository = BaseRepository(context)
        apiListener = mock(APIListener::class.java) as APIListener<Any>
        call = mock(Call::class.java) as Call<Any>
    }

    @Test
    fun `test executeCall with successful response`() {
        val response = Response.success(200, Any())

        `when`(call.enqueue(any())).thenAnswer {
            (it.arguments[0] as Callback<Any>).onResponse(call, response)
        }

        repository.executeCall(call, apiListener)

        verify(apiListener).onLoading(true)
        verify(apiListener).onSuccess(anyOrNull())
        verify(apiListener).onLoading(false)
    }

    @Test
    fun `test executeCall with API error response`() {
        val jsonMediaType = "application/json; charset=utf-8".toMediaType()
        val errorBody = okhttp3.ResponseBody.create(jsonMediaType, "{\"error\": \"Error message\"}")
        val response = Response.error<Any>(400, errorBody)

        `when`(call.enqueue(any())).thenAnswer {
            (it.arguments[0] as Callback<Any>).onResponse(call, response)
        }

        repository.executeCall(call, apiListener)

        verify(apiListener).onLoading(true)
        verify(apiListener).onError("Error message")
        verify(apiListener).onLoading(false)
    }


    @Test
    fun `test executeCall with network failure`() {
        `when`(call.enqueue(any())).thenAnswer {
            (it.arguments[0] as Callback<Any>).onFailure(call, RuntimeException("Failed"))
        }

        repository.executeCall(call, apiListener)

        verify(apiListener).onLoading(true)
        verify(apiListener).onError("Um erro inesperado ocorreu. Tente novamente mais tarde.: Failed")
        verify(apiListener).onLoading(false)
    }

}
