package com.tomasrepcik.screenshotprotection.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tomasrepcik.screenshotprotection.SecuredContent

class SecureFlagActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE
        )

        setContent {
            SecuredContent(text = "Flag secure")
        }
    }
}

