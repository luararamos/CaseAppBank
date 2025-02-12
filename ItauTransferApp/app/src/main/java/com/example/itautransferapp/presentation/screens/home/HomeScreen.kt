package com.example.itautransferapp.presentation.screens.home

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import com.example.itautransferapp.R
import com.example.itautransferapp.presentation.BankStatementActivity
import com.example.itautransferapp.presentation.NewTransferActivity
import com.example.itautransferapp.presentation.components.BankAccountToolbar
import com.example.itautransferapp.presentation.components.CardBank
import com.example.itautransferapp.presentation.components.CardNewTransfer
import com.example.itautransferapp.ui.theme.CORNER_RADIUS_16
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING
import com.example.itautransferapp.ui.theme.MEDIUM_SPACER
import com.example.itautransferapp.ui.theme.SUPER_PADDING
import com.example.itautransferapp.ui.theme.SUPER_SPACER
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val context = LocalContext.current
    val viewModel: HomeViewModel = getViewModel()

    val state = viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.getUserData()
    }

    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.colorBackground))
    ) {
        BankAccountToolbar(
            photo = state.photo,
            name = state.name,
            ag = state.ag,
            cc = state.account
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(CORNER_RADIUS_16))
                .background(Color.White)
                .padding(vertical = MEDIUM_PADDING, horizontal = SUPER_PADDING)

        ) {
            Column {
                Spacer(modifier = Modifier.height(MEDIUM_SPACER))
                CardBank(name = state.name, accountBalance = state.value) {
                    val intent = Intent(context, BankStatementActivity::class.java)
                    context.startActivity(intent)
                }
                Spacer(modifier = Modifier.height(SUPER_SPACER))
                CardNewTransfer() {
                    val intent = Intent(context, NewTransferActivity::class.java)
                    context.startActivity(intent)
                }

            }
        }
    }
}