package com.example.project01

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_task.*

class Task : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)

        open_browser.setOnClickListener {
            val url = text_url.text.toString()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        text_url.addTextChangedListener(object : TextWatcher {
            // afterTextchanged는 입력이 끝난 이후에 처리하고 싶을 때 사용한다.
            override fun afterTextChanged(s: Editable?) {
                if (s.toString() === "https") {
                    Log.d("Edittext_Event", "ssl")
                }
                Log.d("Edittext_Event", "after " + s)
            }

            /*
            beforeTextChanged는 입력에 의해 변화가 생기기 전에 처리하고 싶을 때 사용한다.
            start는 변하기 전의 글자 수
            count는 이전 텍스트에서 after의 크기만큼 바뀌는 글자의 수이다.
            after는 변할 글자의 수
             */
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("Edittext_Event", "before " + s)
                Log.d("Edittext_Event", "before start" + start)
                Log.d("Edittext_Event", "before count" + count)
                Log.d("Edittext_Event", "before after" + after)
            }

            /*
            onTextChanged는 변화와 동시에 처리를 해주고 싶을 때 사용한다.
            start는 변하기 전의 글자 수
            before는 이전 텍스트에서 변한 글자의 수
            count는 변할 글자의 수
            */
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("Edittext_Event", "on " + s)
                Log.d("Edittext_Event", "on start" + start)
                Log.d("Edittext_Event", "on before" + before)
                Log.d("Edittext_Event", "on count" + count)
            }
        })
    }
}