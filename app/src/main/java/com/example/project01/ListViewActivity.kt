package com.example.project01

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_list_view_activity.*
import kotlinx.android.synthetic.main.items.view.*

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view_activity)

        val carList = ArrayList<Car>()
        for (i in 0 until 20) {
            carList.add(Car("${i}번째 자동차", "${i}마력 엔진"))
        }

        val adapter = ListViewAdapter(carList, this@ListViewActivity)
        listView.adapter = adapter
    }
}

class ListViewAdapter(val carList: ArrayList<Car>, val context: Context): BaseAdapter() {
    /*
    position에 해당하는 데이터를 보여주는 View를 가져온다.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.items, null)
        val carNameView = view.car_name
        val carEngineView = view.car_engine

        carNameView.text = carList.get(position).name
        carEngineView.text = carList.get(position).engine

        return view
    }

    /*
    postion에 있는 아이템을 가져온다.
     */
    override fun getItem(position: Int): Any {
        return carList.get(position)
    }

    /*
    postion에 있는 아이템의 id를 가져온다.
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /*
    아이템의 갯수를 가져온다.
     */
    override fun getCount(): Int {
        return carList.size
    }
}