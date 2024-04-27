package com.example.itautransferapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.example.itautransferapp.R
import com.example.itautransferapp.ui.theme.CORNER_RADIUS_16
import com.example.itautransferapp.ui.theme.EXTRA_SMALL_PADDING
import com.example.itautransferapp.ui.theme.FONT_16
import com.example.itautransferapp.ui.theme.FONT_24
import com.example.itautransferapp.ui.theme.HEIGHT_CARD
import com.example.itautransferapp.ui.theme.SUPER_PADDING
import com.example.itautransferapp.ui.theme.WIDTH_CARD

@Composable
fun CardBank(
    name: String,
    accountBalance: String,
    onClick: () -> Unit
) {

    Box(modifier = Modifier
        .size(width = WIDTH_CARD, height = HEIGHT_CARD)
        .clip(RoundedCornerShape(CORNER_RADIUS_16))
        .clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_card),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
        Column(
            modifier = Modifier.padding(SUPER_PADDING)
        ) {

            Text(
                text = name,
                style = TextStyle(
                    fontSize = FONT_24
                ),
                color = colorResource(id = R.color.colorTextPrimary)
            )
            Spacer(
                modifier = Modifier.weight(1f)
            )
            Text(
                text = stringResource(id = R.string.account_balance),
                style = TextStyle(
                    fontSize = FONT_16
                ),
                color = colorResource(id = R.color.colorTextPrimary)
            )
            Text(
                text = "R$ $accountBalance",
                style = TextStyle(
                    fontSize = FONT_24
                ),
                color = colorResource(id = R.color.colorTextPrimary)
            )
            Spacer(
                modifier = Modifier.weight(2f)
            )
            Row {
                Text(
                    text = stringResource(id = R.string.see_bank_statement),
                    style = TextStyle(
                        fontSize = FONT_16
                    ),
                    color = colorResource(id = R.color.colorTextPrimary)
                )
                Spacer(
                    modifier = Modifier.width(EXTRA_SMALL_PADDING)
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_simple_right),
                    contentDescription = null,
                    tint = Color.White
                )
            }

        }
    }
}