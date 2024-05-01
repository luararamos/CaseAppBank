package com.example.itautransferapp.presentation.components

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.itautransferapp.R
import com.example.itautransferapp.ui.theme.CORNER_RADIUS_16
import com.example.itautransferapp.ui.theme.ELEVATION_16
import com.example.itautransferapp.ui.theme.FONT_14
import com.example.itautransferapp.ui.theme.FONT_16
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING

@Composable
fun CardBalanceAmount( typeAgent: String, value: String) {

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
            Column {

                Text(
                    modifier = Modifier
                        .padding(horizontal = MEDIUM_PADDING, vertical = MEDIUM_PADDING),
                    text = stringResource(id = R.string.balance_amount, typeAgent, value),
                    color = colorResource(id = R.color.colorTextSecondary),
                    fontSize = FONT_16,
                )

            }

        }
}