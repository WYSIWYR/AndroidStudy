package com.example.project01

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_shared_preference.*

class SharedPreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)
        val sharedPreference = getSharedPreferences( "sp1", Context.MODE_PRIVATE)

        /*
        SharedPreference는 여려개를 만들 수 있기 때문에 이름은 고유해야 한다.
        SharedPreference를 생성할 때는 모드를 설정해줘야 한다.
        모드의 종류
        MODE_PRIVATE: 생성한 앱에서만 사용가능
        MODE_APPEND: 기존 preference의 끝부분에 추가
         */

        spSaveButton.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreference.edit()
            editor.putString("Hellow", "안녕하세요!")
            editor.putString("Goodbye", "안녕가세요!")
            editor.apply()
        }

        spLoadButton.setOnClickListener {
            val str1 = sharedPreference.getString("Hellow", "데이터가 없습니다");
            val str2 = sharedPreference.getString("Goodbye", "데이터가 없습니다");
            Log.d("sp1", "Hellow: ${str1}")
            Log.d("sp1", "Goodbye: ${str2}")
        }

        // 특정 키의 값만 삭제한다.
        spDeleteButton.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreference.edit()
            editor.remove("Hellow")
            editor.apply()
        }

        // 모든 값을 삭제한다.
        spDeleteAllButton.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreference.edit()
            editor.clear()
            editor.apply()
        }
    }
}