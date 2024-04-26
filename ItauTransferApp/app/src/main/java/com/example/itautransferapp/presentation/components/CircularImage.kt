package com.example.itautransferapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.itautransferapp.ui.theme.CORNER_RADIUS_32
import com.example.itautransferapp.ui.theme.MIN_INPUT_HEIGHT
import com.example.itautransferapp.ui.theme.MIN_INPUT_WIDTH

@Composable
fun CircularImage(
    image: Int
) {

    Box(
        modifier = Modifier
            .size(width = MIN_INPUT_WIDTH, height = MIN_INPUT_HEIGHT)
            .clip(RoundedCornerShape(CORNER_RADIUS_32))
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )


    }

}