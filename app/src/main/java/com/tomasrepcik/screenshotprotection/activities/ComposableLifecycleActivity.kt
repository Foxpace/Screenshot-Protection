package com.tomasrepcik.screenshotprotection.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.tomasrepcik.screenshotprotection.SecuredContent

class ComposableLifecycleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isObscured by remember { mutableStateOf(false) }
            val lifecycleOwner = LocalLifecycleOwner.current
            val lifecycleObserver = LifecycleEventObserver { _, event ->
                when (event) {
                    Lifecycle.Event.ON_RESUME -> isObscured = false
                    Lifecycle.Event.ON_PAUSE -> isObscured = true
                    else -> Unit
                }
            }
            DisposableEffect(key1 = lifecycleOwner) {
                onDispose {
                    lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
                }
            }
            lifecycleOwner.lifecycle.addObserver(lifecycleObserver)

            if (isObscured) {
                Surface {}
            } else {
                SecuredContent(text = "Composable lifecycle")
            }
        }
    }
}

