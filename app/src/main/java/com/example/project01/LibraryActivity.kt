package com.example.project01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_library.*

class LibraryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)

        /*
        이미지를 url로 추가할려면 인터넷 접근 권한이 있어야 한다.
        manifest 파일에
        <uses-permission android:name="android.permission.INTERNET"/>
        을 입력하면 된다.
         */
        Glide.with(this@LibraryActivity)
            .load(R.drawable.siamese_cat)
            .into(glide_test1)

        Glide.with(this@LibraryActivity)
            .load(R.drawable.welsh_corgi)
            .into(glide_test2)
    }
}