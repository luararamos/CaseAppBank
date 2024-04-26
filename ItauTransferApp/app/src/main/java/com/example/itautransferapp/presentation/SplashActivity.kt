package com.example.itautransferapp.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.itautransferapp.R
import com.example.itautransferapp.ui.theme.CustomRobotoCondensed
import com.example.itautransferapp.ui.theme.FONT_16
import com.example.itautransferapp.ui.theme.MAX_INPUT_HEIGHT
import com.example.itautransferapp.ui.theme.MAX_INPUT_WIDTH
import com.example.itautransferapp.ui.theme.MEDIUM_PADDING

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.colorBackground)),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(MEDIUM_PADDING)
                ) {
                    Image(
                        modifier = Modifier
                            .size(MAX_INPUT_WIDTH, MAX_INPUT_HEIGHT)
                            .padding(MEDIUM_PADDING),
                        painter = painterResource(id = R.drawable.ic_itau),
                        contentDescription = null
                    )
                    CircularProgressIndicator(
                        modifier = Modifier.padding(MEDIUM_PADDING),
                        color = colorResource(id = R.color.colorPrimary)
                    )
                    Text(
                        modifier = Modifier.padding(MEDIUM_PADDING),
                        text = stringResource(id = R.string.itau_tranfer),
                        style = androidx.compose.ui.text.TextStyle(
                            fontFamily = CustomRobotoCondensed,
                            fontSize = FONT_16
                        ),
                        color = colorResource(id = R.color.colorTextPrimary)
                    )

                }
            }

        }
        Handler().postDelayed({
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }, 1200)
    }
}