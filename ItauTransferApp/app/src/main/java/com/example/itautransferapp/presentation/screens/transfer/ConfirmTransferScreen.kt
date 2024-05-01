package com.example.itautransferapp.presentation.screens.transfer

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.itautransferapp.R
import com.example.itautransferapp.presentation.ProofBankTransactionActivity
import com.example.itautransferapp.presentation.components.ButtonAction
import com.example.itautransferapp.presentation.components.CardInformationTransfer
import com.example.itautransferapp.presentation.data.TransactionDetails

@Composable
fun ConfirmTransferScreen(){
    val context = LocalContext.current
    val transaction = TransactionDetails(
        nome = "Carlos Silva",
        cpf = "123.456.789-00",
        agencia = "001",
        conta = "1234-5",
        data = "18/04/2024",
        hora = "10am",
        valor = "500,00"
    )
    Column {
        CardInformationTransfer(transaction = transaction)
        
        ButtonAction(text = stringResource(id = R.string.confirm_transfer)) {
            val intent = Intent(context, ProofBankTransactionActivity::class.java)
            context.startActivity(intent)
            (context as? Activity)?.finish()
        }

    }
}

data class Transacao(
    val nome: String,
    val cpf: String,
    val agencia: String,
    val conta: String,
    val data: String,
    val hora: String,
    val valor: String
)