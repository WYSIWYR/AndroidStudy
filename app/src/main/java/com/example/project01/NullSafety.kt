package com.example.project01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class NullSafety : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_null_safety)

        //num1은 null이 될 수 있다.
        val num1: Int? = null
        val num2: Int = 10
        // num1과 num2를 더하고 싶을 때 num1에서 ? 연산자를 사용해 plus를 호출하면 된다.
        val num3 = num1?.plus(num2)
        Log.d("num", "num3: " + num3)

        /*
        엘비스 연산자(Nullable a ?: basic): a가 null일 경우 basic을 반환하고 null이 아니면 a를 반환한다.
         */
        val num4 = num1 ?: 10
        Log.d("num", "num4: " + num4)

        /*
        !! 연산자는 변수 뒤에 붙이면 Null이 아니라고 보장한다.
        밑의 예제는 num1이 null이기 때문에 오류가 발생한다.
         */
        val num5:Int = num1!! + 10
    }
}