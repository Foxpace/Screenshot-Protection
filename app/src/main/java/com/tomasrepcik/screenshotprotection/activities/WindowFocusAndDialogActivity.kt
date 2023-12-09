package com.tomasrepcik.screenshotprotection.activities

import android.app.Dialog
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tomasrepcik.screenshotprotection.R
import com.tomasrepcik.screenshotprotection.SecuredContent

class WindowFocusAndDialogActivity : ComponentActivity() {

    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecuredContent(text = "Window Focus + Flag secure implementation")
        }
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            dialog?.dismiss()
            dialog = null
        } else {
            Dialog(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen).also { dialog ->
                this.dialog = dialog
                dialog.window?.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                )
                dialog.setContentView(R.layout.activity_obscure_layout)
                dialog.show()
            }

        }
    }
}

