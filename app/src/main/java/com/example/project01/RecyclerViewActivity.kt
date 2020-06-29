package com.example.project01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recycler_view.*
import kotlinx.android.synthetic.main.items.view.*

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val carList = ArrayList<Car>()
        for (i in 0 until 20) {
            carList.add(Car("${i}번째 자동차", "${i}마력 엔진"))
        }
        val adapter = RecyclerViewAdapter(carList, LayoutInflater.from(this@RecyclerViewActivity))
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
    }
}

/*
순서 onCreateViewHolder(ViewHolder 생성) -> onBindViewHolder
 */
class RecyclerViewAdapter (
    val carList: ArrayList<Car>,
    val inflater: LayoutInflater
): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    /*
    뷰를 받아와 carName, carEngine을 초기화한다.
     */
    class ViewHolder(carView: View): RecyclerView.ViewHolder(carView) {
        val carName: TextView
        val carEngine: TextView

        init {
            carName = carView.car_name
            carEngine = carView.car_engine
        }
    }

    /*
    뷰를 생성해서 뷰홀더에 넣어서 반환한다.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.items, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    /*
    뷰홀더를 가져와 carName, carEngine의 뷰에 carName, carEngine 값을 넣어준다.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.carName.text = carList.get(position).name
        holder.carEngine.text = carList.get(position).engine
    }
}