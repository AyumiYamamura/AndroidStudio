package com.example.kotlinstopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    val handler = Handler()
    var timeValue = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //View要素を変数に代入
        val timeText = findViewById<TextView>(R.id.timeLabel)
        val startButton = findViewById<Button>(R.id.start)
        val stopButton = findViewById<Button>(R.id.stop)
        val resetButton = findViewById<Button>(R.id.reset)

        val runnable = object : Runnable
    }
}
