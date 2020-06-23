package com.example.project01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class Lateinit : AppCompatActivity() {
    class lateInit(val num: Int) {
    }

    lateinit var lateinitVar: lateInit
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lateinit)

        lateinitVar = lateInit(10)
        Log.d("num", "num" + lateinitVar.num)
    }
}