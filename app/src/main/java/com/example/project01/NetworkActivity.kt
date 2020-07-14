package com.example.project01

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_network.*
import kotlinx.android.synthetic.main.students.view.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NetworkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        NetworkTask(
            recyclerView = studentRecyclerView,
            inflater = LayoutInflater.from(this@NetworkActivity),
            context = this@NetworkActivity
        ).execute()
    }
}

class NetworkTask(
    val recyclerView: RecyclerView,
    val inflater: LayoutInflater,
    val context: Context
):AsyncTask<Any?, Any?, Array<Student>?>() {
    override fun doInBackground(vararg p0: Any?): Array<Student> {
        val urlString: String = "http://mellowcode.org/json/students/"
        val url: URL = URL(urlString)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        // 요청의 header를 설정한다.
        connection.setRequestProperty("Content-Type", "application/json")

        /*
        요청의 결과 값이 HTTP_OK일 경우에 InputStreamReader와 BufferReader를 통해 값을 가져온다.
        네트워크 통신은 Byte방식으로 통신하기 때문에 사람이 읽을 수 있게 UTF-8 방식으로 변환해준다.
         */
        var buffer = ""
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(
                InputStreamReader(
                    connection.inputStream,
                    "UTF-8"
                )
            )
            buffer = reader.readLine()
        }

        return Gson().fromJson(buffer, Array<Student>::class.java)
    }

    // 작업이 끝나고 Main Thread로 돌아가기 때문에 UI에 접근이 가능하다.
    override fun onPostExecute(result: Array<Student>?) {
        super.onPostExecute(result)
        val adapter = NetworkRecyclerViewAdapter(result!!, inflater)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}

class NetworkRecyclerViewAdapter(
    val studentList: Array<Student>,
    val inflater: LayoutInflater
): RecyclerView.Adapter<NetworkRecyclerViewAdapter.ViewHolder>() {
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