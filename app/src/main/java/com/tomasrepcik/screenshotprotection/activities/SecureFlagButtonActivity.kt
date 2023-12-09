package com.tomasrepcik.screenshotprotection.activities

import android.app.Activity
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tomasrepcik.screenshotprotection.ui.theme.ScreenshotProtectionTheme

class SecureFlagButtonActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current
            val flag = remember {
                mutableStateOf(false)
            }
            ScreenshotProtectionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Press button to toggle Secure flag",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )

                        Button(onClick = {
                            if (flag.value) {
                                window.clearFlags(
                                    WindowManager.LayoutParams.FLAG_SECURE
                                )
                            } else {
                                window.addFlags(
                                    WindowManager.LayoutParams.FLAG_SECURE
                                )
                            }
                            flag.value = flag.value.not()
                        }) {
                            Text(text = "Secure flag: ${flag.value}")
                        }

                        Button(onClick = {
                            (context as Activity).finish()
                        }) {
                            Text(text = "Back")
                        }
                    }
                }
            }
        }
    }
}



