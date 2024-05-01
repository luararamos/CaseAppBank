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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.itautransferapp.R
import com.example.itautransferapp.common.CurrencyAmountInputVisualTransformation
import com.example.itautransferapp.ui.theme.FONT_16
import com.example.itautransferapp.ui.theme.SUPER_PADDING

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldManager(
    label: String,
    isError: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    errorMessage: String = stringResource(id = R.string.generic_error),
    leadingIconId: Int? = null,
    trailingIconId: Int? = null,
    initialText: String = "",
    onTextChange: (String) -> Unit
) {
    val textState = remember { mutableStateOf(initialText) }
    val passwordVisible = remember { mutableStateOf(false) }
    val isPasswordField = trailingIconId != null
    val errorText = if (isError) errorMessage else ""
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
                textState.value = newText
                onTextChange(newText)
            },
            isError = isError,
            visualTransformation = if (isPasswordField && !passwordVisible.value) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = if (isPasswordField) KeyboardType.Password else KeyboardType.Text),
            keyboardActions = KeyboardActions(onDone = { }),
            supportingText = {
                if (isError) {
                    Text(text = errorText, color = colorResource(id = R.color.colorError))
                }
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
                    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                        trailingIcon?.let { Icon(painter = it, contentDescription = null) }
                    }
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
    onTextChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {

    val textState = remember { mutableStateOf("") }
    val errorText = if (isError) "Erro! Verifique a entrada." else ""

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
                textState.value = newText
                onTextChange(newText)

            },
            value = textState.value,
            isError = isError,
            keyboardOptions = keyboardOptions,
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
    isError: Boolean,
    clickAlertDialog: () -> Unit
) {
    val textState = remember { mutableStateOf("") }
    val borderColor = if (!isError) Color.Gray else Color.Red // Altere a cor da borda se houver um erro


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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoneyTextField(
    label: String,
    value:String,
    valueError: MutableState<String>,
    isError: Boolean,
    onTextChange: (String) -> Unit,
) {

    var text by remember { mutableStateOf(value) }
    val errorText = if (isError) valueError.value else ""

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
                text = newText
                onTextChange(newText)

            },
            visualTransformation = CurrencyAmountInputVisualTransformation(),
            value = text,
            isError = isError,
            keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Number),
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


