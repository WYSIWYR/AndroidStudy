package com.example.project01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_retrofit.*
import kotlinx.android.synthetic.main.students.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)

        service.getStudentList().enqueue(object : Callback<ArrayList<Student>> {
            override fun onFailure(call: Call<ArrayList<Student>>, t: Throwable) {
                Log.d("retrofit", "ERROR: ${call.isCanceled}")
            }

            override fun onResponse(
                call: Call<ArrayList<Student>>,
                response: Response<ArrayList<Student>>
            ) {
                if (response.isSuccessful) {
                    val studentList = response.body()

                    val adapter = RetrofitRecyclerViewAdapter(
                        studentList = studentList!!,
                        inflater = LayoutInflater.from(this@RetrofitActivity)
                    )
                    retrofitRecyclerView.adapter = adapter
                    retrofitRecyclerView.layoutManager = LinearLayoutManager(this@RetrofitActivity)
                }
            }
        })

/*        val student = Student(id = 888, name = "바밤바", age = 22, intro = "밤맛바밤바")
        service.createStudent(student).enqueue(object : Callback<Student> {
            override fun onFailure(call: Call<Student>, t: Throwable) {
                Log.d("retrofit", "ERROR: ${call.isCanceled}")
            }

            override fun onResponse(call: Call<Student>, response: Response<Student>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("retrofit", "body: ${body?.name}")
                }
            }
        })*/
    }
}

class RetrofitRecyclerViewAdapter(
    val studentList: ArrayList<Student>,
    val inflater: LayoutInflater
): RecyclerView.Adapter<RetrofitRecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(studentView: View): RecyclerView.ViewHolder(studentView) {
        val studentId: TextView = studentView.studentId
        val studentName: TextView = studentView.studentName
        val studentAge: TextView = studentView.studentAge
        val studentIntro: TextView = studentView.studentIntro

        init {
            studentView.setOnClickListener {
                val position = adapterPosition
                val intro = studentList.get(position).intro
                Log.d("Intro", "Intro: $intro")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.students, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.studentId.text = studentList.get(position).id.toString()
        holder.studentName.text = studentList.get(position).name
        holder.studentAge.text = studentList.get(position).age.toString()
        holder.studentIntro.text = studentList.get(position).intro
    }
}
