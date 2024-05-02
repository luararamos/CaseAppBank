package com.example.itautransferapp.presentation.screens.transfer

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.itautransferapp.data.local.PreferencesManager
import com.example.itautransferapp.data.remote.Api
import com.example.itautransferapp.data.remote.RetrofitClient
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

class TransferViewModel (
    applicationContext: Context,
) : ViewModel() {
    private var _saldo = mutableStateOf("")
    val user = PreferencesManager.getLastLoggedUser(applicationContext)
    val api = RetrofitClient.getService(Api::class.java)


    // Esta função lida com o evento de clique do botão e realiza a validação em vários campos
    fun handleButtonClick(
        textValueError: MutableState<String>,
        isErrorValor: MutableState<Boolean>,
        textSelectedDialog: String,
        isErrorAccount: MutableState<Boolean>,
        isErrorName: MutableState<Boolean>,
        isErrorCpf: MutableState<Boolean>,
        isErrorMsg: MutableState<Boolean>,
        isErrorDialogBanco: MutableState<Boolean>,
        isErrorDialogTypeTransaction: MutableState<Boolean>,
        textValueTreansfer: MutableState<String>,
        textStateBank: MutableState<String>,
        textTypeTreansfer: MutableState<String>,
        textValueName: MutableState<String>,
        textValueCPF: MutableState<String>,
        textIndexInt: MutableState<Int>,
        navController: NavController
    ) {
        // Valida os campos e retorna uma lista de campos inválidos
        val invalidFields = validateFields(
            isErrorAccount,
            isErrorName,
            isErrorCpf,
            isErrorMsg,
            isErrorValor,
            isErrorDialogBanco,
            isErrorDialogTypeTransaction,
            textSelectedDialog,
            textValueTreansfer,
            textStateBank,
            textTypeTreansfer,
            textValueName,
            textValueCPF,
            textIndexInt,
        )

        val validationResult = validateTransferValue(textTypeTreansfer.value, textValueTreansfer.value)
        // Se a validação falhar, atualiza o estado do erro e a mensagem de erro
        if (validationResult.isNotEmpty()) {
            textValueError.value = validationResult
            isErrorValor.value = true
        }

        // Se houver campos inválidos, loga os campos inválidos
        if (invalidFields.isNotEmpty()) {
            Log.d("TAG", invalidFields.toString())
        } else {
            viewModelScope.launch {
                if(user != null){
                    if (checkAmount(user.id.toInt(), textValueTreansfer.value)) {
                        // O usuário tem saldo suficiente, navegue para a próxima tela
                        val gson = Gson()
                        val dataMap = mapOf(
                            "nome" to textValueName.value,
                            "cpf" to textValueCPF.value,
                            "agencia" to textIndexInt.value.toString(),
                            "conta" to textSelectedDialog,
                            "valor" to textValueTreansfer.value,
                            "valorAtualizado" to calcularSaldo(_saldo.value,textValueTreansfer.value),
                        )
                        val data = gson.toJson(dataMap)
                        navController.navigate("confirmTransferScreen/$data")
                    } else {
                        // O usuário não tem saldo suficiente, mostre uma mensagem de erro
                        textValueError.value = "Saldo insuficiente, seu saldo R\$${_saldo.value}"
                        isErrorValor.value = true
                    }
                }

            }

        }
    }


    // Valida o valor da transferência com base no tipo de transferência
    private fun validateTransferValue(transferType: String, transferValue: String): String {
        val valueInCents = transferValue.toIntOrNull() ?: return "Valor inválido"
        val value = valueInCents / 100.0
        return when (transferType) {
            "PIX" -> if (value in 1.0..5000.0) "" else "O valor para PIX deve ser entre R$1.00 e R$5.000"
            "DOC" -> if (value in 5000.0..10000.0) "" else "O valor para DOC deve ser entre R$5.000 e R$10.000"
            "TED" -> if (value >= 10000.0) "" else "O valor para TED deve ser maior ou igual a R$10.000"
            else -> "Tipo de transferência inválido"
        }
    }

    // Valida os campos e retorna uma lista de campos inválidos
    private fun validateFields(
        isErrorAccount: MutableState<Boolean>,
        isErrorName: MutableState<Boolean>,
        isErrorCpf: MutableState<Boolean>,
        isErrorMsg: MutableState<Boolean>,
        isErrorValor: MutableState<Boolean>,
        isErrorDialogBanco: MutableState<Boolean>,
        isErrorDialogTypeTransaction:MutableState<Boolean>,
        textSelectedDialog: String,
        textValueTreansfer: MutableState<String>,
        textStateBank: MutableState<String>,
        textTypeTreansfer: MutableState<String>,
        textValueName: MutableState<String>,
        textValueCPF: MutableState<String>,
        textIndexInt: MutableState<Int>
    ): List<String> {
        val invalidFields = mutableListOf<String>()
        if (textSelectedDialog.all { it.isDigit() } && (textSelectedDialog.length == 8)) {
            isErrorAccount.value = false
        } else {
            isErrorAccount.value = true
            invalidFields.add("Account")
        }
        if (textValueName.value.length < 4 || textValueName.value.isEmpty()) {
            isErrorName.value = true
            invalidFields.add("Name")
        } else {
            isErrorName.value = false
        }
        if (textValueCPF.value.length < 11 || textValueCPF.value.length > 11 || textValueCPF.value.isEmpty()) {
            isErrorCpf.value = true
            invalidFields.add("CPF")
        } else {
            isErrorCpf.value = false
        }
        if (textValueTreansfer.value.isEmpty()) {
            isErrorMsg.value = true
            invalidFields.add("Message")
        } else {
            isErrorMsg.value = false
        }
        if (textStateBank.value=="Selecione o Banco") {
            isErrorDialogBanco.value = true
            invalidFields.add("Banco")
        } else {
            isErrorDialogBanco.value = false
        }
        if (textTypeTreansfer.value=="Selecione") {
            isErrorDialogTypeTransaction.value = true
            invalidFields.add("Type")
        } else {
            isErrorDialogTypeTransaction.value = false
        }
        if (textValueTreansfer.value.isEmpty()) {
            isErrorValor.value = true
            invalidFields.add("Value")
        } else {
            isErrorValor.value = false
        }
        return invalidFields
    }
    fun getTextIndex(textSelected: String): Int {
        return when (textSelected) {
            "123 - Banco do Brasil" -> 123
            "222 - Nubank" -> 222
            "342 - Itaú Unibanco" -> 342
            "221 - Banco Pan" -> 221
            "225 - Banco Original" -> 225
            else -> 0 // valor padrão se nenhum dos casos acima corresponder
        }
    }

    private suspend fun checkAmount(id: Int, transferValue: String): Boolean {
        val valueInCents = transferValue.toIntOrNull() ?: return false
        val value = valueInCents / 100.0
        return try {
            val accountResponses = api.getAccount(id)

            // Verifica se o saldo da conta é maior ou igual ao valor da transferência
            accountResponses.any {
                _saldo.value=  it.amount
                it.amount.toDouble() >= value
            }

        } catch (e: Exception) {
            false
        }
    }

    private fun calcularSaldo(saldo: String, valorTransferido: String): String {
        val valorFormatado = String.format(Locale.US, "%.2f", valorTransferido.toInt() / 100.0)
        val novoSaldo = saldo.toDouble() - valorFormatado.toDouble()
        return "R$%.2f".format(novoSaldo)
    }
}