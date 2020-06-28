package com.example.project01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_contact_detail.*

class ContactDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)

        getPersonInfo()

        back_picture.setOnClickListener{
            //  뒤로가기 버튼과 똑같은 동작을 한다.
            onBackPressed()
        }
    }

    fun getPersonInfo() {
        val name = intent.getStringExtra("name")
        val number = intent.getStringExtra("number")

        detail_name.text = name
        detail_number.text = number
    }
}