package com.example.project01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_thread.*

class ThreadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        val runnable: Runnable = object : Runnable {
            override fun run() {
                Log.d("Custom_Thread", "thread 1")
            }
        }

        // 람다를 사용하지않고 스레드를 사용하는 방법 1
        val thread1: Thread = Thread(runnable)
        thread1_button.setOnClickListener {
            thread1.start()
        }

        // 람다를 사용하지않고 스레드를 사용하는 방법 2(람다 사용이 권장된다.)
        Thread(object: Runnable {
            override fun run() {
                Log.d("Custom_Thread", "thread 2")
            }
        }).start()

        /*
        람다 형식으로 스레드를 사용하는 방법
        UI에 관련된 작업은 메인 스레드에서만 처리할 수 있기 때문에
        runOnUiThread를 통해 메인 스레드로 이동해서 처리한다.
         */
        Thread(Runnable {
            Thread.sleep(1000)
            Log.d("Custom_Thread", "thread 3")
            runOnUiThread{
                thread1_button.setBackgroundColor(getColor(R.color.red))
            }
        }).start()
    }
}