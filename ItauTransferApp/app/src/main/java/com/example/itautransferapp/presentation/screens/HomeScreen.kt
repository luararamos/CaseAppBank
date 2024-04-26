package com.example.itautransferapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.itautransferapp.R
import com.example.itautransferapp.presentation.components.CardBank
import com.example.itautransferapp.presentation.components.CardNewTransfer
import com.example.itautransferapp.presentation.components.BankAccountToolbar
import com.example.itautransferapp.ui.theme.CORNER_RADIUS_16
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING
import com.example.itautransferapp.ui.theme.SUPER_PADDING

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    Column(
        modifier = Modifier
            .background(colorResource(id = R.color.colorBackground))
    ) {
        BankAccountToolbar(
            photo = R.drawable.img_transfer,
            name = "Carlos Daniel",
            ag = "342",
            cc = "322390-0"
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(CORNER_RADIUS_16))
                .background(Color.White)
                .padding(vertical = MEDIUM_PADDING, horizontal = SUPER_PADDING)

        ) {
            Column {
                Spacer(modifier = Modifier.height(16.dp))
                CardBank(name = "Carlos Daniel", accountBalance = "3.450,00")
                Spacer(modifier = Modifier.height(32.dp))
                CardNewTransfer()
            }
        }
    }
}