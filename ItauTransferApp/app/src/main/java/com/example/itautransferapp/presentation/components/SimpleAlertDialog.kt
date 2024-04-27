package com.example.itautransferapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.itautransferapp.R
import com.example.itautransferapp.ui.theme.CORNER_RADIUS_16
import com.example.itautransferapp.ui.theme.MIN_SMALL_PADDING

@Composable
fun SimpleDialog(
    showDialogState: MutableState<Boolean>,
    title: String,
    list: Array<String>,
    indiceSelected: MutableState<Int>,
    onSelected: (String, Int) -> Unit,
) {

    fun onDismiss() {
        showDialogState.value = false
    }
    if (showDialogState.value) {
        Dialog(onDismissRequest = { onDismiss() } ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(CORNER_RADIUS_16))
                    .background(Color.White)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Text(
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        text = title,
                        color = colorResource(id = R.color.colorTextSecondary),
                    )
                    Icon(
                        modifier = Modifier.clickable {
                            showDialogState.value = false
                            onSelected("", 2)
                        },
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = null
                    )
                }

                LazyColumn {
                    itemsIndexed(list ){index, text->
                        val isSelected = index == indiceSelected.value
                        if (isSelected){
                            SelectedText(text = text, selected = isSelected){txt->
                                onSelected(txt, index)
                                showDialogState.value = false
                            }
                        } else{
                            Text(
                                modifier = Modifier.weight(1f)
                                    .padding(MIN_SMALL_PADDING)
                                    .clickable {
                                        onSelected(text, index)
                                        showDialogState.value = false
                                    },
                                text = text,
                                color = colorResource(id = R.color.colorTextTertiary),
                            )
                        }

                    }
                }
            }
        }
    }

}