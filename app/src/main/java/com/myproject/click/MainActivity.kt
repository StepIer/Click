package com.myproject.click

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    /*val - final
    var - common
    i?:0
    */
    lateinit var btnStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnStart = findViewById(R.id.btn_start)
        btnStart.setOnClickListener{
            Toast.makeText(this, "Start!", Toast.LENGTH_SHORT).show()
        }
        val t = TextView(this).apply { text = "сонаос" }

        findViewById<ConstraintLayout>(R.id.texttipo).addView(t)

    }
}