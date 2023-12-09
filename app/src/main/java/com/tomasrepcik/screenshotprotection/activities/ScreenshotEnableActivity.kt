package com.tomasrepcik.screenshotprotection.activities

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.tomasrepcik.screenshotprotection.SecuredContent

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
class ScreenshotEnableActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRecentsScreenshotEnabled(false)
        setContent {
            SecuredContent(text = "SetRecentsScreenshotEnabled")
        }
    }

}

