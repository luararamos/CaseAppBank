package com.example.itautransferapp.presentation.screens.transfer

import android.os.Build
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itautransferapp.data.remote.model.User
import com.example.itautransferapp.domain.APIListener
import com.example.itautransferapp.domain.repository.TransferRepository
import com.example.itautransferapp.domain.repository.UserRepository
import com.example.itautransferapp.presentation.screens.transfer.model.Transacao
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class ConfirmTransferViewModel(
    private val userRepository: UserRepository,
    private val transferRepository: TransferRepository
) : ViewModel() {

    private var _stateName = mutableStateOf("")
    val stateName: State<String> = _stateName


    private var _valueAccount = mutableStateOf("0.00")
    private var _stateGET = mutableStateOf("")

    private var _isLoading = mutableStateOf(false)
    private var _isAccountFound = mutableStateOf(false)

    val isLoading: State<Boolean> = _isLoading
    val stateGET: State<String> = _stateGET

    val isAccountFound = MutableLiveData(false)

    fun getTransaction(dataString: String?): Transacao {
        val currentDateTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val date = currentDateTime.format(dateFormatter)
        val time = currentDateTime.format(timeFormatter)

        val gson = Gson()
        val type = object : TypeToken<Map<String, String>>() {}.type
        val dataMap: Map<String, String> = gson.fromJson(dataString ?: "", type)

        val valor = dataMap["valor"] ?: "0"
        val cpf = dataMap["cpf"] ?: ""
        val valorCliente = calcularSaldo(_valueAccount.value, valor)
        val nome = (dataMap["nome"]
            ?: "").replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        val valorFormatado = String.format("R$%.2f", valor.toInt() / 100.0)
        val formattedCpf = "${cpf.substring(0, 3)}.${cpf.substring(3, 6)}.${cpf.substring(6, 9)}-${
            cpf.substring(
                9,
                11
            )
        }"


        val transacao = Transacao(
            nome = stateName.value,
            cpf = formattedCpf,
            agencia = dataMap["agencia"] ?: "",
            conta = dataMap["conta"] ?: "",
            data = date,
            hora = time,
            valor = valorFormatado,
            msg = dataMap["msg"] ?: "",
            valor_cliente = valorCliente,
            valor_atualizado = dataMap["valorAtualizado"] ?: ""
        )

        return transacao

    }

    fun checkAccount(account: String) {
        _stateGET.value = "Carregando..."
        _isLoading.value = true

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val users = transferRepository.getUsers()
                for (id in 1..users.size) {
                    val accountResponses = transferRepository.getAccountResponses(id)

                    for (accountResponse in accountResponses) {
                        Log.d("TAG", "account: ${accountResponse.userId}")
                        if (accountResponse.account == account) {
                            if (transferRepository.getUserId() == accountResponse.userId) {
                                Log.d("TAG", "igaus")
                                _isLoading.value = false
                                isAccountFound.postValue(false)
                                _stateGET.value = "Outra conta por favor!"

                            }
                            isAccountFound.postValue(true)

                            _valueAccount.value = accountResponse.amount
                            _isLoading.value = false

                            findNameById(accountResponse.id)
                            break
                        }


                    }

                    delay(1000)

                    if (isAccountFound.value == true) break
                }


            } catch (e: Exception) {
                _stateGET.value = "Erro ao buscar a conta!"
                _isLoading.value = false
            } finally {
                _stateGET.value =
                    if (isAccountFound.value == false) "Falha na transfêrencia,verifique os dados." else "transfêrencia realizada com sucesso!"
                _isLoading.value = false
            }
        }
    }

    private fun calcularSaldo(saldo: String, valorTransferido: String): String {
        val valorFormatado = String.format(Locale.US, "%.2f", valorTransferido.toInt() / 100.0)
        val novoSaldo = saldo.toDouble() + valorFormatado.toDouble()
        Log.d("oskdoksd", novoSaldo.toString())
        return "R$%.2f".format(novoSaldo)
    }

    fun findNameById(accountId: String){
        userRepository.getUser(object : APIListener<User> {
            override fun onSuccess(response: User) {
                val user = response.find {
                    it.id.trim() == accountId
                }
                if (user != null) {
                    _stateName.value = user.name
                } else {
                    _stateName.value = "Nome não encontrado"
                }
                _isLoading.value = false
            }

            override fun onError(response: String) {
            }

            override fun onLoading(stateLoading: Boolean) {
            }

        })
    }
}