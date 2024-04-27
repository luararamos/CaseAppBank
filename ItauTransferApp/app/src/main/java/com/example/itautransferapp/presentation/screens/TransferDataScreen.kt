package com.example.itautransferapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import com.example.itautransferapp.R
import com.example.itautransferapp.presentation.components.ButtonAction
import com.example.itautransferapp.presentation.components.CardContact
import com.example.itautransferapp.presentation.components.OutlinedTextFieldAlertDialog
import com.example.itautransferapp.presentation.components.OutlinedTextFieldManager
import com.example.itautransferapp.presentation.components.SimpleCheckBox
import com.example.itautransferapp.presentation.components.SimpleDialog
import com.example.itautransferapp.ui.theme.CORNER_RADIUS_16
import com.example.itautransferapp.ui.theme.ELEVATION_16
import com.example.itautransferapp.ui.theme.EXTRA_SMALL_PADDING
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING

@Composable
fun TransferDataScreen(navController: NavController) {
    val checked = remember { mutableStateOf(true) }
    val mockListContacts: Array<String> =
        arrayOf("Maria", "Fernando", "Nicia", "Luara", "Lise", "Monica")
    val textTitleDialog: String = "Selecione um banco"
    val listBank: Array<String> = arrayOf(
        "123 - Bando do Brasil",
        "222 - Nubank",
        "342 - Itaú Unibanco",
        "221 - Banco Pan",
        "225 - Banco Original"
    )
    val listTypeTransfer: Array<String> = arrayOf(
        "TED",
        "DOC",
        "PIX",
    )
    var textSelectedDialog = ""
    val showBankDialog = remember { mutableStateOf(false) }
    val showTypeTranferDialog = remember { mutableStateOf(false) }
    val textStateBank = remember { mutableStateOf("") }
    val textTypeTreansfer = remember { mutableStateOf("") }
    val textIndexInt = remember { mutableStateOf(2) }

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
                            colorResource(id = R.color.colorBackgroundWhite))
                ) {
                    OutlinedTextFieldAlertDialog(
                        label = stringResource(id = R.string.select_bank),
                        iconId = R.drawable.ic_arrow_right_gray,
                        text = textStateBank.value,
                        clickAlertDialog = {
                            showBankDialog.value = true
                        }
                    )

                    OutlinedTextFieldManager(
                        label = stringResource(id = R.string.writing_cont),
                        isError = false, maxLength = 5,
                    )

                    OutlinedTextFieldManager(
                        label = stringResource(id = R.string.name_receptor),
                        isError = false, maxLength = 5,
                    )

                    OutlinedTextFieldManager(
                        label = stringResource(id = R.string.cpf_receptor),
                        isError = false, maxLength = 5,
                    )

                    OutlinedTextFieldManager(
                        label = stringResource(id = R.string.value_transfer),
                        isError = false, maxLength = 5,
                    )

                    OutlinedTextFieldAlertDialog(
                        label = stringResource(id = R.string.type_transfer),
                        iconId = R.drawable.ic_arrow_right_gray,
                        text = textTypeTreansfer.value,
                        clickAlertDialog = {
                            showTypeTranferDialog.value = true
                        }
                    )

                    OutlinedTextFieldManager(
                        label = stringResource(id = R.string.messenger),
                        isError = false, maxLength = 5
                    )

                    SimpleCheckBox(
                        onCheckedChange = { checked.value = it },
                        label = stringResource(id = R.string.save_contact)
                    )

                    ButtonAction(text = stringResource(id = R.string.next)) {
                        navController.navigate("confirmTransferScreen")
                    }

                }

            }
        }

        item{
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
    ) { textSelected , indice->
        textIndexInt.value = indice
        textStateBank.value = textSelected
        showBankDialog.value = false
    }

    SimpleDialog(
        showDialogState = showTypeTranferDialog ,
        title = textTitleDialog,
        list = listTypeTransfer,
        indiceSelected = textIndexInt
    ) { textSelected, indice ->
        textTypeTreansfer.value = textSelected
        showTypeTranferDialog.value = false
    }
}