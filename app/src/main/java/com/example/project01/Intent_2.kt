package com.example.project01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_2.*

class Intent_2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_2)

        intent_text_2.setOnClickListener {
            /*
            intent는 값을 꺼낼 때 자료형을 설정해줘야 한다.
            값을 가져올 때 null이 있을 수 있기 때문에 기본 값을 설정해줘야 한다.
             */
            val number1: Int = intent.getIntExtra("number1", 0)
            val number2: Int = intent.getIntExtra("number2", 0)

            Log.d("number 1", "" + number1)
            Log.d("number 2", "" + number2)

            val numberResult: Int = number1 + number2
            val numberResultIntent = Intent()
            numberResultIntent.putExtra("result", numberResult)
            setResult(Activity.RESULT_OK, numberResultIntent)
            /*
            Intent는 Stack 형식으로 저장된다.
            Intent 1을 실행 시켜 Intent 2를 호출하면 Intent 1 위에 Intent 2에 저장된다.
            finish 함수를 호출하면 액티비티가 종료되면서 intent 2가 종료되고 intent 1 액티비티로 돌아간다.
            */
            this.finish() // 액티비티  종료
        }
    }
}