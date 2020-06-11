package com.example.project01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_listener.*

class Listener : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listener)

        /*
        xml은 기본적으로 뷰가 정적이기 때문에 액티비티에서 뷰를 동적으로 바꿔준다.
        뷰를 조작하는 함수들과 뷰를 가져오는 방법을 알아보자

        뷰를 액티비티에 가져오는 방법
        1. 자바에서 사용하던 방법. 직접 찾아서 가져온다.
        val textView: TextView = findViewById(R.id.hellow)
        2. xml을 import해 가져오는 방법
        import kotlinx.android.synthetic.main.activity_listener.* 이 import 되면 view의 id로 접근할 수 있다.
        hellow
        */

        /*
        뷰를 조작하는 함수들은 리스너가 특정 이벤트가 발생하면 호출되면 뷰를 조작하는 경우가 많다.
        val click = object: View.onClickListener{
            override fun onClick(v: View?) {
                Log.d("TestClick", "Text is clicked")
            }
        }

        hellow.setOnClickListener(click)

        View의 onClickListener 인터페이스를 오버라이드해 오브젝트를 생성한뒤 setOnClickListener에 넣어준다.
        위의 오브젝트 생성과 함수사용을을 간단하게 작성한 것이 아래 익명함수이다.

        hellow.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                Log.d("TextClick", "Text is clicked!!")
            }
        }
        위의 익명함수를 람다 함수를 사용하여 간단하게 작성한 것이다.
        id에 해당하는 view를 클릭하면 안드로이드 OS가 view에 연결되어있는 리스너들에게 클릭되었다고
        알리면 setOnClickListener가 반응을한다.
         */
        hellow.setOnClickListener {
            /*
            TextView의 text를 설정하는 방법
            1. setText 함수를 사용한다.
            hellow.setText("안녕하세욤")
            2. text에 문자열을 저장한다.
            hellow.text = "안녕하세욤"
             */
            hellow.text = "안녕하세욤"
            /*
            ImageView의 image를 설정하는 방법
            image.setImageResource(R.drawable.siamese_cat)
             */
            image.setImageResource(R.drawable.siamese_cat)
            Log.d("TextClick", "Text is clicked!!")
        }
    }
}
