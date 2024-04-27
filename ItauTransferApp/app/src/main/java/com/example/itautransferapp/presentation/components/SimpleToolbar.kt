package com.example.itautransferapp.presentation.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.itautransferapp.R
import com.example.itautransferapp.presentation.NewTransferActivity
import com.example.itautransferapp.ui.theme.CORNER_RADIUS_16
import com.example.itautransferapp.ui.theme.FONT_24
import com.example.itautransferapp.ui.theme.MAX_INPUT_HEIGHT
import com.example.itautransferapp.ui.theme.MAX_INPUT_WIDTH
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING

@Composable
fun SimpleToolbar(title: String) {
    val context = LocalContext.current

    Row(
        modifier = Modifier.padding(MEDIUM_PADDING),
        verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier
                .clickable(onClick = {
                    (context as? Activity)?.finish()
                }),
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = null,
            tint = colorResource(id = R.color.colorTextSecondary)
        )
        Text(
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            text = title,
            fontSize = FONT_24,
            color = colorResource(id = R.color.colorTextSecondary)
        )
    }

}