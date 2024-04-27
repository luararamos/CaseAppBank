package com.example.itautransferapp.presentation.screens

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.itautransferapp.R
import com.example.itautransferapp.presentation.NewTransferActivity
import com.example.itautransferapp.presentation.ProofBankTransactionActivity
import com.example.itautransferapp.presentation.components.ButtonAction
import com.example.itautransferapp.presentation.components.CardInformationTransfer
import com.example.itautransferapp.presentation.components.SimpleToolbar
import com.example.itautransferapp.ui.theme.CORNER_RADIUS_16
import com.example.itautransferapp.ui.theme.ELEVATION_16
import com.example.itautransferapp.ui.theme.FONT_14
import com.example.itautransferapp.ui.theme.FONT_16
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING

@Composable
fun ConfirmTransferScreen(){
    val context = LocalContext.current
    val transaction =Transacao(
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