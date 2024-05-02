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
import com.example.itautransferapp.presentation.screens.transfer.model.Transacao
import com.example.itautransferapp.ui.theme.FONT_24

class ProofBankTransactionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val transaction = intent.getSerializableExtra("transaction") as Transacao
        setContent {

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
                CardBalanceAmount(typeAgent = "Emissor", value = transaction.valor_atualizado)
                CardBalanceAmount(typeAgent = "Receptor", value = transaction.valor_cliente)

                ButtonAction(text = stringResource(id = R.string.finish)) {
                    finish()
                }

            }
        }
    }
}