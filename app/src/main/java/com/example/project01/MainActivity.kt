package com.example.project01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //AppCompatActivity의 onCreate를 호출한다.
        setContentView(R.layout.activity_main) // View를 설정한다. 시작할 떄 한번만 그려도 되기 때문에 onCreate에서 초기화

        //tag는 특정 그룹을 만들어 그 그룹만 볼 수 있다
        Log.d("life_cycle", "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("life_cycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("life_cycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("life_cycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("life_cycle", "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Life_cycle", "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("life_cycle", "onDestroy")
    }
}

