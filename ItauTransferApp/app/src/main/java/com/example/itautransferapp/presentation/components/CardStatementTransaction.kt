package com.example.itautransferapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.itautransferapp.R
import com.example.itautransferapp.presentation.screens.BankTransaction
import com.example.itautransferapp.ui.theme.CORNER_RADIUS_16
import com.example.itautransferapp.ui.theme.ELEVATION_16
import com.example.itautransferapp.ui.theme.FONT_14
import com.example.itautransferapp.ui.theme.FONT_16
import com.example.itautransferapp.ui.theme.FONT_24
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING

@Composable
fun CardStatementTransaction(
    transaction: BankTransaction
) {

    Card(
        modifier = Modifier
            .padding(horizontal = MEDIUM_PADDING, vertical = MEDIUM_PADDING)
            .shadow(elevation = ELEVATION_16, spotColor = Color.Black, shape = RoundedCornerShape(CORNER_RADIUS_16))
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.colorBackgroundWhite))
                .padding(MEDIUM_PADDING)
        ) {
            val (
                textTypeInput,
                textNameInput,
                textValue,
                textValueInput,
                textDateAndHour,
                textDateAndHourInput,
            ) = createRefs()


            Text(
                text = transaction.typeTransaction,
                color = colorResource(id = R.color.colorTextSecondary),
                fontSize = FONT_16,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(ref = textTypeInput) {
                        top.linkTo(parent.top, MEDIUM_PADDING)
                        start.linkTo(parent.start, MEDIUM_PADDING)
                    }
            )
            Text(
                text = stringResource(id = R.string.value),
                color = colorResource(id = R.color.colorTextTertiary),
                fontSize = FONT_14,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(ref = textValue) {
                        top.linkTo(textTypeInput.bottom, MEDIUM_PADDING)
                        start.linkTo(parent.start, MEDIUM_PADDING)
                    }
            )
            Text(
                text = stringResource(id = R.string.date_and_hour),
                color = colorResource(id = R.color.colorTextTertiary),
                fontSize = FONT_14,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(ref = textDateAndHour) {
                        top.linkTo(textValue.bottom, MEDIUM_PADDING)
                        start.linkTo(parent.start, MEDIUM_PADDING)
                    }
            )
            Text(
                text = transaction.name,
                color = colorResource(id = R.color.colorTextSecondary),
                fontSize = FONT_16,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(ref = textNameInput) {
                        top.linkTo(parent.top, MEDIUM_PADDING)
                        end.linkTo(parent.end, 16.dp)
                    }
            )
            Text(
                text = "R$ " + transaction.value,
                color = colorResource(id = R.color.colorTextQuaternary),
                fontSize = FONT_14,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(ref = textValueInput) {
                        top.linkTo(textNameInput.bottom, MEDIUM_PADDING)
                        end.linkTo(parent.end, MEDIUM_PADDING)
                    }
            )

            Text(
                text = transaction.date,
                color = colorResource(id = R.color.colorTextQuaternary),
                fontSize = FONT_14,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .constrainAs(ref = textDateAndHourInput) {
                        top.linkTo(textValueInput.bottom, MEDIUM_PADDING)
                        end.linkTo(parent.end, MEDIUM_PADDING)
                    },
                maxLines = 1

            )

        }
    }
}