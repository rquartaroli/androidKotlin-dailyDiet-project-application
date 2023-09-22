package com.example.dailydietapplication.utils

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View

class StatusBarTransparent {
    companion object {
        fun Activity.setLightStatusBarAndTransparentStatusBar() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                window.statusBarColor = Color.TRANSPARENT
            }
        }
    }
}