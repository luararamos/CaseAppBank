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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.itautransferapp.R
import com.example.itautransferapp.presentation.screens.Transacao
import com.example.itautransferapp.ui.theme.CORNER_RADIUS_16
import com.example.itautransferapp.ui.theme.ELEVATION_16
import com.example.itautransferapp.ui.theme.FONT_14
import com.example.itautransferapp.ui.theme.FONT_16
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING

@Composable
fun CardInformationTransfer(
    transaction: Transacao
) {
    Column {
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

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.colorBackgroundWhite))
                    .padding(MEDIUM_PADDING)
            ) {
                val (
                    textName,
                    textCpf,
                    textAgency,
                    textCont,
                    textDate,
                    textHour,
                    textValue,
                    textNameInput,
                    textCpfInput,
                    textAgencyInput,
                    textContInput,
                    textDateInput,
                    textHourInput,
                    textValueInput,
                ) = createRefs()


                Text(
                    text = stringResource(id = R.string.name),
                    color = colorResource(id = R.color.colorTextTertiary),
                    fontSize = FONT_14,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(ref = textName) {
                            top.linkTo(parent.top, MEDIUM_PADDING)
                            start.linkTo(parent.start, MEDIUM_PADDING)
                        }
                )
                Text(
                    text = stringResource(id = R.string.cpf),
                    color = colorResource(id = R.color.colorTextTertiary),
                    fontSize = FONT_14,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(ref = textCpf) {
                            top.linkTo(textName.bottom, MEDIUM_PADDING)
                            start.linkTo(parent.start, MEDIUM_PADDING)
                        }
                )
                Text(
                    text = stringResource(id = R.string.agency),
                    color = colorResource(id = R.color.colorTextTertiary),
                    fontSize = FONT_14,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(ref = textAgency) {
                            top.linkTo(textCpf.bottom, MEDIUM_PADDING)
                            start.linkTo(parent.start, MEDIUM_PADDING)
                        }
                )
                Text(
                    text = stringResource(id = R.string.cont),
                    color = colorResource(id = R.color.colorTextTertiary),
                    fontSize = FONT_14,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(ref = textCont) {
                            top.linkTo(textAgency.bottom, MEDIUM_PADDING)
                            start.linkTo(parent.start, MEDIUM_PADDING)
                        }
                )
                Text(
                    text = stringResource(id = R.string.date),
                    color = colorResource(id = R.color.colorTextTertiary),
                    fontSize = FONT_14,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(ref = textDate) {
                            top.linkTo(textCont.bottom, MEDIUM_PADDING)
                            start.linkTo(parent.start, MEDIUM_PADDING)
                        }
                )
                Text(
                    text = stringResource(id = R.string.hour),
                    color = colorResource(id = R.color.colorTextTertiary),
                    fontSize = FONT_14,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(ref = textHour) {
                            top.linkTo(textDate.bottom, MEDIUM_PADDING)
                            start.linkTo(parent.start, MEDIUM_PADDING)
                        }
                )
                Text(
                    text = stringResource(id = R.string.value),
                    color = colorResource(id = R.color.colorTextSecondary),
                    fontSize = FONT_16,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(ref = textValue) {
                            top.linkTo(textHour.bottom, MEDIUM_PADDING)
                            start.linkTo(parent.start, MEDIUM_PADDING)
                        }
                )
                Text(
                    text = transaction.nome,
                    color = colorResource(id = R.color.colorTextSecondary),
                    fontSize = FONT_14,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(ref = textNameInput) {
                            top.linkTo(parent.top, MEDIUM_PADDING)
                            end.linkTo(parent.end, 16.dp)
                        }
                )
                Text(
                    text = transaction.cpf,
                    color = colorResource(id = R.color.colorTextSecondary),
                    fontSize = FONT_14,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(ref = textCpfInput) {
                            top.linkTo(textNameInput.bottom, MEDIUM_PADDING)
                            end.linkTo(parent.end, MEDIUM_PADDING)
                        }
                )
                Text(
                    text = transaction.agencia,
                    color = colorResource(id = R.color.colorTextSecondary),
                    fontSize = FONT_14,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(ref = textAgencyInput) {
                            top.linkTo(textCpfInput.bottom, MEDIUM_PADDING)
                            end.linkTo(parent.end, MEDIUM_PADDING)
                        }
                )
                Text(
                    text = transaction.conta,
                    color = colorResource(id = R.color.colorTextSecondary),
                    fontSize = FONT_14,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(ref = textContInput) {
                            top.linkTo(textAgencyInput.bottom, MEDIUM_PADDING)
                            end.linkTo(parent.end, MEDIUM_PADDING)
                        }
                )
                Text(
                    text = transaction.data,
                    color = colorResource(id = R.color.colorTextSecondary),
                    fontSize = FONT_14,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(ref = textDateInput) {
                            top.linkTo(textContInput.bottom, MEDIUM_PADDING)
                            end.linkTo(parent.end, MEDIUM_PADDING)
                        }
                )
                Text(
                    text = transaction.hora,
                    color = colorResource(id = R.color.colorTextSecondary),
                    fontSize = FONT_14,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(ref = textHourInput) {
                            top.linkTo(textDateInput.bottom, MEDIUM_PADDING)
                            end.linkTo(parent.end, MEDIUM_PADDING)
                        }
                )
                Text(
                    text = transaction.valor,
                    color = Color.Red,
                    fontSize = FONT_16,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .constrainAs(ref = textValueInput) {
                            top.linkTo(textHourInput.bottom, MEDIUM_PADDING)
                            end.linkTo(parent.end, MEDIUM_PADDING)
                        }
                )

            }

        }
    }
}