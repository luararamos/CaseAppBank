package com.example.itautransferapp.presentation.screens.transfer

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.example.itautransferapp.R
import com.example.itautransferapp.presentation.components.ButtonAction
import com.example.itautransferapp.presentation.components.CardContact
import com.example.itautransferapp.presentation.components.MoneyTextField
import com.example.itautransferapp.presentation.components.OutlinedTextFieldAlertDialog
import com.example.itautransferapp.presentation.components.OutlinedTextFieldManager
import com.example.itautransferapp.presentation.components.SimpleCheckBox
import com.example.itautransferapp.presentation.components.SimpleDialog
import com.example.itautransferapp.ui.theme.CORNER_RADIUS_16
import com.example.itautransferapp.ui.theme.ELEVATION_16
import com.example.itautransferapp.ui.theme.EXTRA_SMALL_PADDING
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING
import org.koin.androidx.compose.getViewModel

@Composable
fun TransferDataScreen(navController: NavController) {


    val viewModel: TransferViewModel = getViewModel()

    //IsError
    val isErrorAccount = remember { mutableStateOf(false) }
    val isErrorName = remember { mutableStateOf(false) }
    val isErrorCpf = remember { mutableStateOf(false) }
    val isErrorValor = remember { mutableStateOf(false) }
    val isErrorMsg = remember { mutableStateOf(false) }
    val isErrorDialogBanco = remember { mutableStateOf(false) }
    val isErrorDialogTypeTransaction = remember { mutableStateOf(false) }

    var textSelectedDialog = ""
    val showBankDialog = remember { mutableStateOf(false) }
    val showTypeTranferDialog = remember { mutableStateOf(false) }
    val textStateBank = remember { mutableStateOf("Selecione o Banco") }
    val textTypeTreansfer = remember { mutableStateOf("Selecione") }
    val textValueTreansfer = remember { mutableStateOf("") }
    val textValueName = remember { mutableStateOf("") }
    val textValueCPF = remember { mutableStateOf("") }
    val textValueMSG = remember { mutableStateOf("") }
    val textValueError = remember { mutableStateOf("") }
    var textIndexInt = remember { mutableStateOf(2) }

    val checked = remember { mutableStateOf(true) }
    val mockListContacts: Array<String> =
        arrayOf("Maria", "Fernando", "Nicia", "Luara", "Lise", "Monica")
    val textTitleDialog = "Selecione um banco"
    val listBank: Array<String> = arrayOf(
        stringResource(id = R.string.bd),
        stringResource(id = R.string.nubank),
        stringResource(id = R.string.itau),
        stringResource(id = R.string.bp),
        stringResource(id = R.string.bo),
    )
    val listTypeTransfer: Array<String> = arrayOf(
        stringResource(id = R.string.ted),
        stringResource(id = R.string.doc),
        stringResource(id = R.string.pix),
    )


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(MEDIUM_PADDING, MEDIUM_PADDING),
    ) {
        item {
            Row {
                Text(
                    modifier = Modifier
                        .weight(1f),
                    text = stringResource(id = R.string.recent_contact),
                    color = colorResource(id = R.color.colorTextTertiary)
                )
                Text(
                    text = stringResource(id = R.string.add_new_contact),
                    color = colorResource(id = R.color.colorTextQuaternary)
                )
            }
        }

        item {
            LazyRow {
                items(mockListContacts) { all ->
                    CardContact(contact = all)
                }
            }
        }
        item {
            Card(
                modifier = Modifier
                    .padding(horizontal = MEDIUM_PADDING, vertical = MEDIUM_PADDING)
                    .fillMaxWidth()
                    .shadow(
                        elevation = ELEVATION_16,
                        shape = RoundedCornerShape(corner = CornerSize(CORNER_RADIUS_16)),
                        spotColor = Color.Black
                    )
                    .background(
                        color = colorResource(id = R.color.colorBackgroundWhite),
                        shape = RoundedCornerShape(corner = CornerSize(CORNER_RADIUS_16))
                    ),
                shape = RoundedCornerShape(corner = CornerSize(CORNER_RADIUS_16))
            ) {
                Column(
                    modifier = Modifier
                        .background(
                            colorResource(id = R.color.colorBackgroundWhite)
                        )
                ) {
                    OutlinedTextFieldAlertDialog(
                        label = stringResource(id = R.string.select_bank),
                        iconId = R.drawable.ic_arrow_right_gray,
                        text = textStateBank.value,
                        isError = isErrorDialogBanco.value,
                        clickAlertDialog = {
                            showBankDialog.value = true
                        }
                    )

                    OutlinedTextFieldManager(
                        label = stringResource(id = R.string.writing_cont),
                        isError = isErrorAccount.value,
                        onTextChange = { it ->
                            if (it.all { it.isDigit() } && (it.length == 8)) {
                                isErrorAccount.value = false
                                textSelectedDialog = it
                            } else {
                                isErrorAccount.value = true
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    )

                    OutlinedTextFieldManager(
                        label = stringResource(id = R.string.name_receptor),

                        isError = isErrorName.value,
                        onTextChange = { it ->
                            textValueName.value = it
                            isErrorName.value = it.length < 4 || it.isEmpty()
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

                        )

                    OutlinedTextFieldManager(
                        label = stringResource(id = R.string.cpf_receptor),
                        isError = isErrorCpf.value,
                        onTextChange = { it ->
                            textValueCPF.value = it
                            isErrorCpf.value = it.length < 11
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    )
                    MoneyTextField(
                        label = stringResource(id = R.string.value_transfer),
                        value = textValueTreansfer.value,
                        valueError = textValueError,
                        isError = isErrorValor.value,
                        onTextChange = {
                            if (it.isEmpty()) {
                                isErrorValor.value = true
                                textValueError.value = "valor não pode ser R$0.00"
                            } else {
                                textValueError.value = ""
                            }

                            textValueTreansfer.value = it
                        },

                        )

                    OutlinedTextFieldAlertDialog(
                        label = stringResource(id = R.string.type_transfer),
                        iconId = R.drawable.ic_arrow_right_gray,
                        text = textTypeTreansfer.value,
                        isError = isErrorDialogTypeTransaction.value,
                        clickAlertDialog = {

                            showTypeTranferDialog.value = true
                        }
                    )
                    OutlinedTextFieldManager(
                        label = stringResource(id = R.string.messenger),
                        isError = isErrorMsg.value,
                        onTextChange = {
                            textValueMSG.value = it
                            isErrorMsg.value = it.isEmpty()
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    )

                    SimpleCheckBox(
                        onCheckedChange = { checked.value = it },

                        label = stringResource(id = R.string.save_contact)
                    )
                    //validateFields()

                    ButtonAction(text = stringResource(id = R.string.next)) {
                        viewModel.handleButtonClick(
                            textValueError,
                            isErrorValor,
                            textSelectedDialog,
                            isErrorAccount,
                            isErrorName,
                            isErrorCpf,
                            isErrorMsg,
                            isErrorDialogBanco,
                            isErrorDialogTypeTransaction,
                            textValueTreansfer,
                            textStateBank,
                            textTypeTreansfer,
                            textValueName,
                            textValueCPF,
                            textIndexInt,
                            navController
                        )
                    }


                }

            }
        }

        item {
            Spacer(
                modifier = Modifier.padding(EXTRA_SMALL_PADDING)
            )
        }

    }
    SimpleDialog(
        showDialogState = showBankDialog,
        title = textTitleDialog,
        list = listBank,
        indiceSelected = textIndexInt
    ) { textSelected, indice ->
        textStateBank.value = textSelected
        showBankDialog.value = false


        if (textSelected != "Selecione o Banco") {
            isErrorDialogBanco.value = false
        }
        textIndexInt.value = viewModel.getTextIndex(textSelected)


    }

    SimpleDialog(
        showDialogState = showTypeTranferDialog,
        title = textTitleDialog,
        list = listTypeTransfer,
        indiceSelected = textIndexInt
    ) { textSelected, indice ->
        textTypeTreansfer.value = textSelected
        showTypeTranferDialog.value = false

        if (textSelected != "Selecione") {
            isErrorDialogTypeTransaction.value = false
        }
    }


}
