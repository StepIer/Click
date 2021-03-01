package com.myproject.click

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.app.Service
import android.content.Intent
import android.graphics.Path
import android.os.Build
import android.os.IBinder
import android.view.accessibility.AccessibilityEvent
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.lang.reflect.AccessibleObject


class ClickService : AccessibilityService() {
    override fun onCreate() {
        super.onCreate()

        (0..10).forEach {
            click(544 , 490)
        }
    }

    override fun onServiceConnected() {
        super.onServiceConnected()

    }

    fun click(x: Int, y: Int) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) return
        val path = Path()
        path.moveTo(100f, 1f)
        path.lineTo(x.toFloat(), y.toFloat())


        val builder = GestureDescription.Builder()
        val gestureDescription = builder
            .addStroke(GestureDescription.StrokeDescription(path, 0, 1))
            .build()
        dispatchGesture(gestureDescription, null, null)

    }


    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        TODO("Not yet implemented")
    }

    override fun onInterrupt() {
        TODO("Not yet implemented")
    }

}