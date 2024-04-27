package com.example.itautransferapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.itautransferapp.R
import com.example.itautransferapp.ui.theme.FONT_16
import com.example.itautransferapp.ui.theme.SUPER_PADDING

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldManager(
    label: String,
    isError: Boolean,
    maxLength: Int? = null,
    leadingIconId: Int? = null,
    trailingIconId: Int? = null
) {
    val textState = remember { mutableStateOf("") }
    val errorText = if (isError) "Erro! Verifique a entrada." else ""
    val characterCount = maxLength?.let { "${textState.value.length} / $it" } ?: ""
    val leadingIcon: Painter? = leadingIconId?.let { painterResource(id = it) }
    val trailingIcon: Painter? = trailingIconId?.let { painterResource(id = it) }

    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SUPER_PADDING),
            text = label,
            color = colorResource(id = R.color.colorTextTertiary),
            style = TextStyle(
                fontSize = FONT_16
            ),
        )
        TextField(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(horizontal = SUPER_PADDING),
            value = textState.value,
            onValueChange = { newText ->
                if (maxLength == null || newText.length <= maxLength) {
                    textState.value = newText
                }
            },
            isError = isError,
            keyboardActions = KeyboardActions(onDone = { }),
            supportingText = {
                Text(text = errorText)
            },
            leadingIcon = {
                if (leadingIcon != null) leadingIcon?.let {
                    Icon(
                        painter = it,
                        contentDescription = null
                    )
                }
            },
            trailingIcon = {
                if (trailingIcon != null)
                    trailingIcon?.let { Icon(painter = it, contentDescription = null) }
            },
            maxLines = 1,
            colors = TextFieldDefaults
                .outlinedTextFieldColors(
                    containerColor = Color.White,
                    focusedBorderColor = colorResource(id = R.color.colorPrimary)
                )
        )
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldManager(
    label: String,
    isError: Boolean,
    maxLength: Int? = null,
) {

    val textState = remember { mutableStateOf("") }
    val errorText = if (isError) "Erro! Verifique a entrada." else ""
    val characterCount = maxLength?.let { "${textState.value.length} / $it" } ?: ""
    var textSelectedDialog = ""



    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SUPER_PADDING)
                .background(colorResource(id = R.color.colorBackgroundWhite)),
            text = label,
            color = colorResource(id = R.color.colorTextTertiary),
            style = TextStyle(
                fontSize = FONT_16
            ),
        )
        OutlinedTextField(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(horizontal = SUPER_PADDING),
            onValueChange = { newText ->
                if (maxLength == null || newText.length <= maxLength) {
                    textState.value = newText
                }
            },
            value = textState.value,
            isError = isError,
            keyboardActions = KeyboardActions(onDone = { }),
            supportingText = {
                Text(text = errorText)
            },
            maxLines = 1,
            colors = TextFieldDefaults
                .outlinedTextFieldColors(
                    containerColor = Color.White,
                    focusedBorderColor = colorResource(id = R.color.colorPrimary)
                )
        )
    }
}


@Composable
fun OutlinedTextFieldAlertDialog(
    label: String,
    iconId: Int,
    text: String,
    clickAlertDialog: () -> Unit
) {
    val textState = remember { mutableStateOf("") }
    val borderColor = Color.Gray

    Column(
        modifier = Modifier
            .clickable(onClick = clickAlertDialog),
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SUPER_PADDING)
                .background(colorResource(id = R.color.colorBackgroundWhite)),
            text = label,
            color = colorResource(id = R.color.colorTextTertiary),
            style = TextStyle(
                fontSize = FONT_16
            ),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = SUPER_PADDING)
                .border(1.dp, borderColor, shape = RoundedCornerShape(4.dp))
                .clickable(onClick = clickAlertDialog)
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = text,
                    modifier = Modifier.weight(1f),
                    color = colorResource(id = R.color.colorTextTertiary)
                )
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = null,  // Consider providing a content description
                    tint = colorResource(id = R.color.colorTextTertiary)
                )
            }
        }

    }
}