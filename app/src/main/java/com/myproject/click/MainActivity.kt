package com.myproject.click

import android.accessibilityservice.GestureDescription
import android.annotation.SuppressLint
import android.graphics.Path
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    /*val - final
    var - common
    i?:0
    */
    lateinit var btnStart: Button
    lateinit var btnStop: Button
    lateinit var container: FrameLayout
    lateinit var imgAim: ImageView
    lateinit var textAmount: TextView

    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    var job: Job? = null

    var xDelta = 0
    var yDelta: Int = 0
    var clickAmount: Int  = 0

//    fun click(x: Int, y: Int) {
//
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) return
//        val path = Path()
//        path.moveTo(x.toFloat(), y.toFloat())
//        val builder = GestureDescription.Builder()
//        val gestureDescription = builder
//            .addStroke(GestureDescription.StrokeDescription(path, 10, 10))
//            .build()
//        dispatchGesture(gestureDescription, null, null)
//    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btn_start)
        btnStop = findViewById(R.id.btn_stop)
        container = findViewById(R.id.container)
        imgAim = findViewById(R.id.img_aim)
        imgAim.setOnTouchListener(touchListener)
        textAmount = findViewById(R.id.text_amount)



        btnStop.setOnClickListener() {

            job?.cancel()
        }

        textAmount.setOnClickListener {
           clickAmount++
            textAmount.text = "$clickAmount Clicks on me"
        }
        btnStart.setOnClickListener {
            job?.cancel()
            job = scope.launch() {
                while (true) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@MainActivity, "Start!", Toast.LENGTH_SHORT).show()
                    }

                    delay(2000)
                }
            }
        }

        val t = TextView(this).apply { text = "сонаос" }

        findViewById<ConstraintLayout>(R.id.texttipo).addView(t)
    }

    val touchListener: View.OnTouchListener = object : View.OnTouchListener {
        @SuppressLint("ClickableViewAccessibility")
        override fun onTouch(view: View, event: MotionEvent): Boolean {
            val x = event.rawX.toInt()
            val y = event.rawY.toInt()
            when (event.action and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    val lParams = view.getLayoutParams() as FrameLayout.LayoutParams
                    xDelta = x - lParams.leftMargin
                    yDelta = y - lParams.topMargin
                }
                MotionEvent.ACTION_UP -> {
                    Toast.makeText(applicationContext, "Объект перемещён", Toast.LENGTH_SHORT)
                        .show()
                }
                MotionEvent.ACTION_MOVE -> {

                    if (x - xDelta + view.getWidth() <= container.getWidth()
                        && y - yDelta + view.getHeight() <= container.getHeight()
                        && x - xDelta >= 0
                        && y - yDelta >= 0
                    ) {
                        val layoutParams = view.getLayoutParams() as FrameLayout.LayoutParams
                        layoutParams.leftMargin = x - xDelta
                        layoutParams.topMargin = y - yDelta
                        layoutParams.rightMargin = 0
                        layoutParams.bottomMargin = 0
                        view.setLayoutParams(layoutParams)
                    }
                }
            }
            container.invalidate()
            return true
        }
    }
}



