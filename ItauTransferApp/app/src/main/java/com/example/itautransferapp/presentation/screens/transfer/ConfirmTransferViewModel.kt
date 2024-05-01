package com.example.itautransferapp.presentation.screens.transfer

import android.os.Build
import android.util.Log
import android.view.SurfaceControl.Transaction
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itautransferapp.data.remote.Api
import com.example.itautransferapp.data.remote.RetrofitClient
import com.example.itautransferapp.presentation.screens.transfer.model.Transacao
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ConfirmTransferViewModel : ViewModel() {

    // Estado para armazenar a mensagem de status da busca de conta
    private var _stateGET = mutableStateOf("")
    private var _valueAccount = mutableStateOf("")
    // Estado para armazenar se a busca de conta está em andamento
    private var _isLoading = mutableStateOf(false)
    private var _isAccountFound = mutableStateOf(false)
    // Exposição dos estados para a UI
    val isLoading: State<Boolean> = _isLoading
    val stateGET: State<String> = _stateGET
    val valueAccount: State<String> = _valueAccount
    // Instância do serviço API
    val api = RetrofitClient.getService(Api::class.java)
    // LiveData para armazenar se a conta foi encontrada
    val isAccountFound = MutableLiveData(false)

    // Função para obter os dados da transação a partir de uma string JSON
    fun getTransaction(dataString: String?): Transacao {
        // Obtenção da data e hora atual
        val currentDateTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDateTime.now()
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        // Formatação da data e hora
        val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
        val date = currentDateTime.format(dateFormatter)
        val time = currentDateTime.format(timeFormatter)

        // Deserialização da string JSON para um mapa
        val gson = Gson()
        val type = object : TypeToken<Map<String, String>>() {}.type
        val dataMap: Map<String, String> = gson.fromJson(dataString ?: "", type)

        // Formatação do valor
        val valor = dataMap["valor"] ?: "0"
        val cpf = dataMap["cpf"] ?: ""
        val valordaConta = _valueAccount.value.toDoubleOrNull() ?: 0.0
        val valortranferido = valor.toDoubleOrNull() ?: 0.0
        val valor_cliente = valordaConta + valortranferido
        val nome = (dataMap["nome"] ?: "").replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        val valorFormatado = String.format("R$%.2f", valor.toInt() / 100.0)
        val formattedCpf = "${cpf.substring(0, 3)}.${cpf.substring(3, 6)}.${cpf.substring(6, 9)}-${cpf.substring(9, 11)}"


        // Criação e retorno da instância de Transacao
        val transacao = Transacao(
            nome = nome,
            cpf = formattedCpf,
            agencia = dataMap["agencia"] ?: "",
            conta = dataMap["conta"] ?: "",
            data = date,
            hora = time,
            valor = valorFormatado,
            msg = dataMap["msg"] ?: "",
            valor_cliente=valor_cliente.toString()
        )

        return transacao

    }

    // Função para verificar se uma conta existe
    fun checkAccount(account:String ) {
        // Atualização dos estados
        _stateGET.value="Carregando..."
        _isLoading.value=true

        // Início da busca de conta
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Busca de conta para cada id de 1 a 50
                for (id in 1..50) {
                    val accountResponses = api.getAccount(id)
                    Log.d("TAG", accountResponses.toString())

                    // Verificação se a conta existe na lista de respostas
                    for (accountResponse in accountResponses) {
                        accountResponse.amount
                        Log.d("TAG",  accountResponse.account+","+accountResponse.amount)
                        if (accountResponse.account == account) {
                            Log.d("TAG",  "encontrou!")
                            // Se a conta foi encontrada, atualização do LiveData e término da busca
                            isAccountFound.postValue(true)

                            //pegando o valor da conta do cliente
                            _valueAccount.value=accountResponse.amount


                            _isLoading.value=false
                            break
                        }
                    }

                    // Pausa entre as buscas
                    delay(1000)

                    //assim que encontra a conta ele para
                    if(isAccountFound.value == true)break
                }


            } catch (e: Exception) {
                // Em caso de erro, atualização dos estados e log do erro
                _stateGET.value="Erro ao buscar a conta!"
                _isLoading.value=false
                Log.d("TAG", "Erro ao buscar a conta: ${e.message}")
            }finally {
                _stateGET.value=if (isAccountFound.value == false)"Falha na transfêrencia,verifique os dados." else "transfêrencia realizada com sucesso!"
                _isLoading.value=false
            }
        }
    }
}