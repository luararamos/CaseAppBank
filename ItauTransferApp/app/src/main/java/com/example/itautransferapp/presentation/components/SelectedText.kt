package com.example.itautransferapp.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.example.itautransferapp.R
import com.example.itautransferapp.ui.theme.MIN_SMALL_PADDING
import com.example.itautransferapp.ui.theme.SMALL_PADDING

@Composable
fun SelectedText(text: String, selected: Boolean = false, clickable: (String) -> Unit) {
    Row(
    ) {
        Text(
            modifier = Modifier.weight(1f)
                .padding(MIN_SMALL_PADDING)
                .clickable{
                    clickable(text)
                },
            text = text,
            color = if(selected) colorResource(id = R.color.colorTextQuaternary)
            else colorResource(id = R.color.colorTextTertiary),
        )
        if (selected){
            Icon(
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = null
            )
        }
    }
}