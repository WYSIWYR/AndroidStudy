package com.example.project01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.activity_add_view.*
import kotlinx.android.synthetic.main.items.view.*

class AddViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_view)

        /*
        items 레이아웃을 가져와 텍스트를 입력하고 container에 하나씩 추가한다.
         */
        val container = addview_container
        val inflater = LayoutInflater.from(this@AddViewActivity)
        for (i in 0 until 10) {
            val itemView = inflater.inflate(R.layout.items, null)
            val carNameView = itemView.car_name
            val carEngineView = itemView.car_engine
            carNameView.text = "자동차$i"
            carEngineView.text = "엔진$i"
            container.addView(itemView)
        }
    }
}

class Car(val name: String, val engine: String) {

}