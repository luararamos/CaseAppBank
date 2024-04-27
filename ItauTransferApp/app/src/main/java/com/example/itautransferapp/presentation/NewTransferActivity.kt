package com.example.itautransferapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.itautransferapp.R
import com.example.itautransferapp.presentation.components.SimpleToolbar
import com.example.itautransferapp.presentation.screens.ConfirmTransferScreen
import com.example.itautransferapp.presentation.screens.TransferDataScreen

class NewTransferActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "transferDataScreen") {
                composable("transferDataScreen") {
                    Column {
                        SimpleToolbar(title = stringResource(id = R.string.transfer))
                        TransferDataScreen(navController)
                    }
                }
                composable("confirmTransferScreen") {
                    Column {
                        SimpleToolbar(title = stringResource(id = R.string.confirm_transfer))
                        ConfirmTransferScreen()
                    }
                }
            }
        }
    }
}