package com.example.itautransferapp.presentation.screens.login

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.example.itautransferapp.R
import com.example.itautransferapp.presentation.MainActivity
import com.example.itautransferapp.presentation.components.ButtonAction
import com.example.itautransferapp.presentation.components.TextFieldManager
import com.example.itautransferapp.presentation.screens.home.HomeViewModel
import com.example.itautransferapp.ui.theme.FONT_16
import com.example.itautransferapp.ui.theme.FONT_32
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING
import com.example.itautransferapp.ui.theme.SUPER_PADDING
import org.koin.androidx.compose.getViewModel


@Composable
fun LoginFormScreen() {
    val viewModel: LoginViewModel = getViewModel()
    val context = LocalContext.current

    val state = viewModel.state.value
    val isLoading = viewModel.isLoading.value
    val errorMessage = viewModel.errorMessage.value


    val email by viewModel.email.observeAsState("")
    val emailValid by viewModel.emailValid.observeAsState(true)
    val emailErrorMessage by viewModel.emailErrorMessage.observeAsState("")

    val password by viewModel.password.observeAsState("")
    val passwordValid by viewModel.passwordValid.observeAsState(true)
    val passwordErrorMessage by viewModel.passwordErrorMessage.observeAsState("")


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
            style = TextStyle(
                fontSize = FONT_32
            ),
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MEDIUM_PADDING, horizontal = SUPER_PADDING),
            text = stringResource(id = R.string.welcome),
            color = colorResource(id = R.color.colorTextTertiary),
            style = TextStyle(
                fontSize = FONT_16
            ),
        )
        TextFieldManager(
            label = stringResource(id = R.string.email),
            isError = !emailValid,
            errorMessage = emailErrorMessage,
            leadingIconId = R.drawable.ic_person,
            onTextChange = viewModel::onEmailChanged
        )
        TextFieldManager(
            label = stringResource(id = R.string.password),
            isError = !passwordValid,
            errorMessage= passwordErrorMessage,
            leadingIconId = R.drawable.ic_padlock,
            trailingIconId = R.drawable.ic_observer,
            onTextChange = viewModel::onPasswordChanged
        )

        Spacer(modifier = Modifier.weight(1f))

        ButtonAction(
            text = stringResource(id = R.string.login).uppercase(),
            isLoading = isLoading
        ) {
            viewModel.loadUserData(email, password)
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

    if (state.isNotEmpty()) {
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

    LaunchedEffect(errorMessage) {
        if (errorMessage.isNotEmpty()) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
    }


}