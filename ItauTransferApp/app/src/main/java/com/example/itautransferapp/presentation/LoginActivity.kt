package com.example.itautransferapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.itautransferapp.R
import com.example.itautransferapp.presentation.components.ButtonAction
import com.example.itautransferapp.presentation.components.TextFieldManager
import com.example.itautransferapp.ui.theme.FONT_16
import com.example.itautransferapp.ui.theme.FONT_32
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING
import com.example.itautransferapp.ui.theme.SUPER_PADDING

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MEDIUM_PADDING, horizontal = SUPER_PADDING),
                    text = stringResource(id = R.string.login),
                    color = colorResource(id = R.color.colorTextSecondary),
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = FONT_32
                    ),
                )
                Text(
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = MEDIUM_PADDING, horizontal = SUPER_PADDING),
                    text = stringResource(id = R.string.welcome),
                    color = colorResource(id = R.color.colorTextTertiary),
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = FONT_16
                    ),
                )
                TextFieldManager(
                    label = stringResource(id = R.string.email),
                    isError = false, maxLength = 5,
                    leadingIconId = R.drawable.ic_person

                )
                TextFieldManager(
                    label = stringResource(id = R.string.password),
                    isError = false, maxLength = 5,
                    leadingIconId = R.drawable.ic_padlock,
                    trailingIconId = R.drawable.ic_observer

                )

                Spacer(modifier = Modifier.weight(1f))

                ButtonAction(text = stringResource(id = R.string.login).uppercase()) {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End,
                        text = stringResource(id = R.string.without_account),
                        color = colorResource(id = R.color.colorTextTertiary)
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start,
                        text = stringResource(id = R.string.register),
                        color = colorResource(id = R.color.colorTextSecondary)
                    )
                }
                
                Spacer(
                    modifier = Modifier.weight(1f)
                )
            }

        }
    }
}