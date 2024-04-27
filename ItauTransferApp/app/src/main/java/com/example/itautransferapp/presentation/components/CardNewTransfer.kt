package com.example.itautransferapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.example.itautransferapp.R
import com.example.itautransferapp.ui.theme.CORNER_RADIUS_16
import com.example.itautransferapp.ui.theme.FONT_14
import com.example.itautransferapp.ui.theme.MAX_INPUT_HEIGHT
import com.example.itautransferapp.ui.theme.MAX_INPUT_WIDTH
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING
import com.example.itautransferapp.ui.theme.MIN_INPUT_HEIGHT
import com.example.itautransferapp.ui.theme.MIN_INPUT_WIDTH
import com.example.itautransferapp.ui.theme.MIN_SMALL_PADDING
import com.example.itautransferapp.ui.theme.SMALL_PADDING

@Composable
fun CardNewTransfer(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(width = MAX_INPUT_WIDTH, height = MAX_INPUT_HEIGHT)
            .clip(RoundedCornerShape(CORNER_RADIUS_16))
            .background(colorResource(id = R.color.colorPrimary))
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(MIN_SMALL_PADDING)
        ) {

            Image(
                modifier = Modifier
                    .size(MIN_INPUT_WIDTH, MIN_INPUT_HEIGHT)
                    .padding(SMALL_PADDING),
                painter = painterResource(id = R.drawable.ic_bank),
                contentDescription = null
            )

            Text(
                modifier = Modifier
                    .padding(SMALL_PADDING),
                text = stringResource(id = R.string.new_transfer),
                style = TextStyle(
                    fontSize = FONT_14
                ),
                color = colorResource(id = R.color.colorTextPrimary)
            )

        }
    }
}