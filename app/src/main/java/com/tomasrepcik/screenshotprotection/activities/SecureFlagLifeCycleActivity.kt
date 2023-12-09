package com.tomasrepcik.screenshotprotection.activities

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tomasrepcik.screenshotprotection.SecuredContent

class SecureFlagLifeCycleActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecuredContent(text = "Flag secure with lifecycle")
        }
    }

    override fun onPause() {
        window.addFlags(
            WindowManager.LayoutParams.FLAG_SECURE
        )
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        window.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
    }

}



