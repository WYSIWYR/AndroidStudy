package com.example.project01

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
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
        listView.setOnItemClickListener { adapterView, view, position, id ->
            val carName = (adapter.getItem(position) as Car).name
            val carEngine = (adapter.getItem(position) as Car).engine

            Toast.makeText(this@ListViewActivity, "${carName}의 엔진은 ${carEngine}입니다.", Toast.LENGTH_SHORT).show()
        }
    }
}

class ListViewAdapter(val carList: ArrayList<Car>, val context: Context): BaseAdapter() {
    /*
    position에 해당하는 데이터를 보여주는 View를 가져온다.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(context)
        val view: View
        val holder: ViewHolder

        /*
        convertView가 없는 경우 새로 생성한다(처음 화면을 그릴 때). 그 이후에는 사라지는 뷰를 재사용하여
        보여줘야 하는 뷰를 생성한다.
         */
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.items, null)
            holder = ViewHolder()
            holder.carName = view.car_name
            holder.carEngine = view.car_engine
            view.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            view = convertView
        }

        holder.carName?.text = carList.get(position).name
        holder.carEngine?.text = carList.get(position).engine

        return view
    }

    /*
    postion에 있는 아이템을 가져온다.
     */
    override fun getItem(position: Int): Any {
        return carList[position]
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

class ViewHolder {
    var carName: TextView? = null
    var carEngine: TextView? = null
}