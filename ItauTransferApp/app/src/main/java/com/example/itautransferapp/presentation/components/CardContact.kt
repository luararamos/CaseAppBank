package com.example.itautransferapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.itautransferapp.R
import com.example.itautransferapp.ui.theme.CORNER_RADIUS_16
import com.example.itautransferapp.ui.theme.ELEVATION_16
import com.example.itautransferapp.ui.theme.FONT_16
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING

@Composable
fun CardContact(contact: String) {
    Card(
        modifier = Modifier
            .padding(horizontal = MEDIUM_PADDING, vertical = MEDIUM_PADDING)
            .size(80.dp, 120.dp)
            .shadow(elevation = ELEVATION_16, shape = RoundedCornerShape(CORNER_RADIUS_16), spotColor = Color.Black)
            .background(colorResource(id = R.color.colorBackgroundWhite), shape = RoundedCornerShape(CORNER_RADIUS_16))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                colorResource(id = R.color.colorBackgroundWhite)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(
                modifier = Modifier.weight(1f)
            )
            CircularImage(image = R.drawable.img_transfer)
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = contact,
                fontSize = FONT_16,
            )
            Spacer(
                modifier = Modifier.weight(1f)
            )
        }
    }
}