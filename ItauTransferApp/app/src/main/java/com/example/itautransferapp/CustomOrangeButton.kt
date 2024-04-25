package com.example.itautransferapp

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.itautransferapp.ui.theme.CORNER_RADIUS_20
import com.example.itautransferapp.ui.theme.LARGE_PADDING
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING
import com.example.itautransferapp.ui.theme.SMALL_PADDING
import com.example.itautransferapp.ui.theme.SUPER_PADDING

@Composable
fun CustomOrangeButton(
    text: String,
    arrow: Boolean,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MEDIUM_PADDING, horizontal = SUPER_PADDING),
        onClick = { onClick() },
        contentPadding = PaddingValues(
            vertical = SMALL_PADDING,
            horizontal = LARGE_PADDING
        ),
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.colorPrimary)),
        shape = RoundedCornerShape(CORNER_RADIUS_20),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                text = text,
                color = Color.White
            )
            if (arrow) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = null,
                    tint = Color.White
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomOrangeButton() {
    CustomOrangeButton(
        text = "Meu Botão",
        arrow = false,
        onClick = {  },
    )
}
