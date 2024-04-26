package com.example.itautransferapp.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.itautransferapp.R
import com.example.itautransferapp.presentation.components.ButtonAction
import com.example.itautransferapp.ui.theme.CustomRobotoCondensed
import com.example.itautransferapp.ui.theme.FONT_24
import com.example.itautransferapp.ui.theme.MAX_INPUT_HEIGHT
import com.example.itautransferapp.ui.theme.MAX_INPUT_WIDTH
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING

class WelcomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.colorBackground))
            ) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.background_circle),
                    contentDescription = null
                )


                Image(
                    modifier = Modifier
                        .size(MAX_INPUT_WIDTH, MAX_INPUT_HEIGHT)
                        .padding(MEDIUM_PADDING),
                    painter = painterResource(id = R.drawable.ic_itau),
                    contentDescription = null
                )

                Text(
                    text = stringResource(id = R.string.itau_tranfer),
                    style = androidx.compose.ui.text.TextStyle(
                        fontFamily = CustomRobotoCondensed,
                        fontSize = FONT_24
                    ),
                    color = colorResource(id = R.color.colorTextPrimary)
                )
                Text(
                    modifier = Modifier.padding(MEDIUM_PADDING),
                    text = stringResource(id = R.string.welcome),
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = FONT_24
                    ),
                    color = colorResource(id = R.color.colorTextPrimary)
                )
                Text(
                    modifier = Modifier.padding(MEDIUM_PADDING),
                    textAlign = TextAlign.Center ,
                    text = stringResource(id = R.string.welcome_message),
                    color = colorResource(id = R.color.colorTextTertiary)
                )
                ButtonAction(stringResource(id = R.string.start), true) {
                    startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java))
                    finish()
                }

            }


        }
    }
}