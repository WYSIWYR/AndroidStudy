package com.example.project01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_fragment.*

class Fragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        Log.d("life_cycle", "onCreate")

        /*
        프라그먼트 동적으로 사용하기
        AppCompatActivity에서 supportFragmentManager를 가져온다.
        이후 Transaction을 생성해야 한다.
        Transaction은 작업의 단위이다. 작업의 시작과 끝을 설정해주는 형식으로 프라그먼트를 사용한다.
        작업을 끝내는 방법으로는 대표적으로는 2가지가 있다.
        1. commit() -> 작업을 끝낼 시간이 있으면 실행한다.
        2. commitnow() -> 작업을 바로 끝낸다.
         */
        call_fragment.setOnClickListener {
            val fragment_1: Fragment_1 = Fragment_1()
            val fragmentManager:FragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container, fragment_1)
            fragmentTransaction.commit()
        }
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