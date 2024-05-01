package com.example.itautransferapp.presentation.screens.transfer

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.itautransferapp.R
import com.example.itautransferapp.presentation.ProofBankTransactionActivity
import com.example.itautransferapp.presentation.components.ButtonAction
import com.example.itautransferapp.presentation.components.CardInformationTransfer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun ConfirmTransferScreen(navController:NavController){
    val viewModel: ConfirmTransferViewModel = getViewModel()
    val showDialog = remember { mutableStateOf(false) }
    val dataString: String = remember {
        navController.currentBackStackEntry?.arguments?.getString("data") ?: ""
    }
    val transacao = viewModel.getTransaction(dataString)
    val isLoading=viewModel.isLoading.value
    val isSuccess=viewModel.isAccountFound.value
    val msgStateGet=viewModel.stateGET.value
    val context = LocalContext.current


    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title ={ Text(if (isLoading) "Por favor, aguarde..." else "Finalizado") },
            text = { Text(msgStateGet) },

            icon = {
                Icon(
                    imageVector = if (isSuccess==true) Icons.Filled.CheckCircle else Icons.Rounded.Clear,
                    contentDescription = if (isSuccess==true) "Sucesso" else "Falha"
                )
            },
            confirmButton = {
                if (!isLoading) {
                    Button(onClick = {
                        showDialog.value = false
                        if (isSuccess==true){
                            val intent = Intent(context, ProofBankTransactionActivity::class.java)
                            intent.putExtra("transaction", viewModel.getTransaction(dataString))
                            context.startActivity(intent)
                            (context as? Activity)?.finish()
                        }else{
                            (context as? Activity)?.onBackPressed()
                        }


                    }) {
                        if (isSuccess==true) Text("proxima tela") else Text("verificar dados")
                    }
                }
            }
        )
    }


    Column {
        CardInformationTransfer(transaction = transacao)

        ButtonAction(text = stringResource(id = R.string.confirm_transfer)) {

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.checkAccount(transacao.conta)
            }
            showDialog.value = true

//            val intent = Intent(context, ProofBankTransactionActivity::class.java)
//            context.startActivity(intent)
//            (context as? Activity)?.finish()
        }

    }
}