package com.example.itautransferapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.res.stringResource
import com.example.itautransferapp.R
import com.example.itautransferapp.presentation.components.SimpleToolbar
import com.example.itautransferapp.presentation.screens.ListBankStatementScreen

class BankStatementActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column {
                SimpleToolbar(title = stringResource(id = R.string.bank_statement))
                ListBankStatementScreen()

            }
        }
    }
}