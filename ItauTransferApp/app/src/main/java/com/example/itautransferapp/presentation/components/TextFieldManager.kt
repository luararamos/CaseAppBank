package com.example.itautransferapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
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
                .padding( horizontal = SUPER_PADDING),
            text = label,
            color = colorResource(id = R.color.colorTextTertiary),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = FONT_16
            ),
        )
        TextField(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding( horizontal = SUPER_PADDING),
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

@Composable
fun OutlinedTextFieldManager(
    isError: Boolean,
    maxLength: Int? = null,
    iconId: Int? = null
) {
    val textState = remember { mutableStateOf("") }
    val errorText = if (isError) "Erro! Verifique a entrada." else ""
    val characterCount = maxLength?.let { "${textState.value.length} / $it" } ?: ""
    val icon: Painter? = iconId?.let { painterResource(id = it) }

    OutlinedTextField(
        modifier = Modifier.background(Color.White),
        value = textState.value,
        onValueChange = { newText ->
            if (maxLength == null || newText.length <= maxLength) {
                textState.value = newText
            }
        },
        isError = isError,
        supportingText = {
            Text(text = errorText)
        },

        leadingIcon = {
            if (icon != null)
                icon?.let { Icon(painter = it, contentDescription = null) }
        },
        maxLines = 1,

        )
}
