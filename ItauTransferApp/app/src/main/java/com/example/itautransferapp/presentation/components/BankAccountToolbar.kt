package com.example.itautransferapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.itautransferapp.R
import com.example.itautransferapp.ui.theme.CORNER_RADIUS_32
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING
import com.example.itautransferapp.ui.theme.MIN_INPUT_HEIGHT
import com.example.itautransferapp.ui.theme.MIN_INPUT_WIDTH
import com.google.accompanist.glide.rememberGlidePainter

@Composable
fun BankAccountToolbar(
    photo: String,
    name: String,
    ag: String,
    cc: String
) {
    val painter = rememberGlidePainter(
        request = photo,
        fadeIn = true,
        previewPlaceholder = R.drawable.ic_placeholder
    )

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.colorBackground))
            .padding(16.dp, 32.dp, 16.dp, 32.dp)
    ) {
        val (
            imgPhoto,
            txtName,
            txtBankCont,
            icNotification,
        ) = createRefs()


        Box(
            modifier = Modifier
                .size(width = MIN_INPUT_WIDTH, height = MIN_INPUT_HEIGHT)
                .clip(RoundedCornerShape(CORNER_RADIUS_32))
                .constrainAs(ref = imgPhoto) {
                    top.linkTo(parent.top, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                }
                .size(60.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )

        }

        Text(
            text = "Olá, $name",
            color = colorResource(id = R.color.colorTextPrimary),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(ref = txtName) {
                    top.linkTo(parent.top, 16.dp)
                    start.linkTo(imgPhoto.end, 16.dp)
                }
        )

        Text(
            text = "Ag. $ag, CC$cc",
            color = colorResource(id = R.color.colorTextPrimary),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(ref = txtBankCont) {
                    top.linkTo(txtName.bottom, 16.dp)
                    start.linkTo(imgPhoto.end, 16.dp)
                },
            maxLines = 3
        )

        Image(
            painter = painterResource(id = R.drawable.ic_alert),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(ref = icNotification) {
                    top.linkTo(parent.top, 8.dp)
                    end.linkTo(parent.end, MEDIUM_PADDING)
                    bottom.linkTo(parent.bottom, MEDIUM_PADDING)
                }
                .size(24.dp)
        )
    }
}
