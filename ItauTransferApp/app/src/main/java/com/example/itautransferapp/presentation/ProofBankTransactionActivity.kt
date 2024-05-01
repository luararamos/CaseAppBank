package com.example.itautransferapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.itautransferapp.R
import com.example.itautransferapp.presentation.components.ButtonAction
import com.example.itautransferapp.presentation.components.CardBalanceAmount
import com.example.itautransferapp.presentation.components.CardInformationTransfer
import com.example.itautransferapp.presentation.components.SimpleToolbar
import com.example.itautransferapp.presentation.detailsview.TransactionDetails
import com.example.itautransferapp.ui.theme.FONT_24

class ProofBankTransactionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val transaction = TransactionDetails(
                nome = "Carlos Silva",
                cpf = "123.456.789-00",
                agencia = "001",
                conta = "1234-5",
                data = "18/04/2024",
                hora = "10am",
                valor = "500,00"
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                SimpleToolbar(title = stringResource(id = R.string.proof))
                Image(
                    painter = painterResource(id = R.drawable.img_transfer),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = FONT_24,
                    text = stringResource(id = R.string.successfully_concluded_transfer),
                    color = colorResource(id = R.color.colorSuccess)
                )
                CardInformationTransfer(transaction = transaction)
                CardBalanceAmount( "emissor", "30,00")
                CardBalanceAmount( "receptor", "859,00")

                ButtonAction(text = stringResource(id = R.string.finish)) {
                    finish()
                }

            }
        }
    }
}