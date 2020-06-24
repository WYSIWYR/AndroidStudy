package com.example.project01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_resource.*

class Resource : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resource)

        // 이전에 사용하던 방식
        val greeting = resources.getString(R.string.안냥)
        Log.d("greeting", "greeting: $greeting")

        // 더 간소화된 방식
        val greeting2 = getString(R.string.안냥)
        Log.d("greeting", "greeting2: $greeting2")

        val color = getColor(R.color.red)
        Log.d("color", "color: $color")

        resource_button.setOnClickListener {
            it.setBackgroundColor(getColor(R.color.blue))
        }
    }
}