package com.tomasrepcik.screenshotprotection.activities

import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import com.tomasrepcik.screenshotprotection.R
import com.tomasrepcik.screenshotprotection.SecuredContent

class PauseResumeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_composable_layout)
        findViewById<ComposeView>(R.id.main_composable).setContent {
            SecuredContent(text = "Pause and Resume with XML layout")
        }
    }

    override fun onPause() {
        val mainComposable = findViewById<RelativeLayout>(R.id.main_container)
        val obscureView = layoutInflater.inflate(R.layout.activity_obscure_layout, null)
        mainComposable.addView(obscureView, mainComposable.width, mainComposable.height)
        super.onPause()

    }

    override fun onResume() {
        super.onResume()
        val mainComposable = findViewById<RelativeLayout>(R.id.main_container)
        mainComposable.removeView(findViewById<ComposeView>(R.id.obscure_layout))
    }

}



