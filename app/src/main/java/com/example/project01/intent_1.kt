package com.example.project01

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_intent_1.*

class Intent_1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_1)

        intent_text_1.setOnClickListener {
            /*
             this나 this@Intent_1은 의미가 같지만, this만 넣었을 경우 앱이 크래시가 나는 경우가 있다.
             context를 넣어야 하는 부분은 this@Intenxt1를 사용해야 한다!
             */
            val intent = Intent(this@Intent_1, Intent_2::class.java)
            /*
            Intent에 정보를 담기 위해서는 intent.putExtra를 사용해야한다.
            key, value 방식으로 통신하기 때문에 name 설정이 중요하다. -> Dictionary
             */
            intent.putExtra("number1", 1)
            intent.putExtra("number2", 2)
//            startActivity(intent)

            val intent2 = Intent(this@Intent_1, Intent_2::class.java)
            /*
            위의 코드와 기능적으로는 같다.
            apply는 .으로 호출한 부분에 내부의 코드를 실행한다.
            블록으로 묶어서 사용하기 때문에 코드를 깔끔하게 관리할 수 있다.
             */
            intent2.apply {
                this.putExtra("number1", 1)
                this.putExtra("number2", 2)
            }
            /*
            startActivity는 전달만 하는 요청이기 때문에 반환 값이 없다
            반환 값을 받기 위해서는 startActivityForResult를 사용해야 한다.
            startActivityForResult는 intent에 요청을 보낼 때 각각의 요청을 식별하기 위해 정수 값으로
            requestCode를 전달한다.
             */
//            startActivityForResult(intent2, 200)

            /*
             암시적 인텐트
             인텐트의 액션을 사용한다.
             */
            val intent3 = Intent(Intent.ACTION_VIEW, Uri.parse("https://m.naver.com"))
            startActivity(intent3)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 200) {
            Log.d("Request Code", "" + requestCode)
            Log.d("Result Code", "" + resultCode)
            val result: Int = data!!.getIntExtra("result", 0)
            Log.d("number", "" + result)

        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}