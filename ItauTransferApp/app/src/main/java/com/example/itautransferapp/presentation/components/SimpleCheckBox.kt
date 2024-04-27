package com.example.itautransferapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.itautransferapp.R
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING

@Composable
fun SimpleCheckBox(
    onCheckedChange: (Boolean) -> Unit,
    label: String
) {
    val checkState = remember { mutableStateOf(true) }
    Row(
        modifier = Modifier
            .background(colorResource(id = R.color.colorBackgroundWhite))
            .padding(MEDIUM_PADDING),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .border(
                    width = 2.dp,
                    color = colorResource(id = R.color.colorTextQuaternary) ,
                    shape = MaterialTheme.shapes.small
                )
                .clickable {
                    if (checkState.value) {
                        checkState.value = false
                        onCheckedChange(checkState.value)
                    }else{
                        checkState.value = true
                        onCheckedChange(checkState.value)
                    }
                }
                .padding(2.dp),
            contentAlignment = Alignment.Center
        ) {
            if (checkState.value) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Checked",
                    tint = colorResource(id = R.color.colorTextQuaternary)
                )
            }
        }
        Text(
            text = label,
            color = colorResource(id = R.color.colorTextQuaternary),
            modifier = Modifier
                .weight(1f)
                .padding(MEDIUM_PADDING, vertical = 0.dp)
        )
    }
}