@file:OptIn(ExperimentalMaterial3Api::class)

package com.tomasrepcik.screenshotprotection

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.tomasrepcik.screenshotprotection.activities.ComposableLifecycleActivity
import com.tomasrepcik.screenshotprotection.activities.PauseResumeActivity
import com.tomasrepcik.screenshotprotection.activities.ScreenshotEnableActivity
import com.tomasrepcik.screenshotprotection.activities.SecureFlagActivity
import com.tomasrepcik.screenshotprotection.activities.SecureFlagButtonActivity
import com.tomasrepcik.screenshotprotection.activities.SecureFlagLifeCycleActivity
import com.tomasrepcik.screenshotprotection.activities.WindowFocusAndDialogActivity
import com.tomasrepcik.screenshotprotection.activities.WindowFocusAndSecureFlagActivity
import com.tomasrepcik.screenshotprotection.ui.theme.ScreenshotProtectionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainComposable()
        }
    }
}

@Composable
fun MainComposable() {

    ScreenshotProtectionTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                ActivityButton(text = "Secure flag", activityClass = SecureFlagActivity::class.java)
                ActivityButton(
                    text = "Secure flag with lifecycle",
                    activityClass = SecureFlagLifeCycleActivity::class.java
                )
                ActivityButton(
                    text = "Secure flag with toggle button",
                    activityClass = SecureFlagButtonActivity::class.java
                )
                ActivityButton(
                    text = "Set recent screenshot",
                    activityClass = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) ScreenshotEnableActivity::class.java else null
                )
                ActivityButton(
                    text = "Window focus + Secure flag",
                    activityClass = WindowFocusAndSecureFlagActivity::class.java
                )
                ActivityButton(
                    text = "Window focus + Dialog",
                    activityClass = WindowFocusAndDialogActivity::class.java
                )
                ActivityButton(
                    text = "Pause/Resume lifecycle + composable inside XML",
                    activityClass = PauseResumeActivity::class.java
                )
                ActivityButton(
                    text = "Composable lifecycle",
                    activityClass = ComposableLifecycleActivity::class.java
                )
            }
        }
    }
}

@Composable
fun <T> ActivityButton(text: String, activityClass: Class<T>?) {
    val context = LocalContext.current
    Button(modifier = Modifier.padding(vertical = 8.dp), onClick = {
        if (activityClass != null) {
            context.startActivity(Intent(context, activityClass))
        } else {
            Toast.makeText(context, "$text is not available on this SDK", Toast.LENGTH_SHORT).show()
        }
    }) {
        Text(text = text, textAlign = TextAlign.Center)
    }
}