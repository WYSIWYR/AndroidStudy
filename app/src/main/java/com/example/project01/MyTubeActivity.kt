package com.example.project01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_my_tube.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyTubeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tube)

        (application as MasterApplication).service.getVideoList().enqueue(
            object : Callback<ArrayList<Video>> {
                override fun onFailure(call: Call<ArrayList<Video>>, t: Throwable) {
                    Log.d("Error", "Error: $t")
                }

                override fun onResponse(
                    call: Call<ArrayList<Video>>,
                    response: Response<ArrayList<Video>>
                ) {
                    if (response.isSuccessful) {
                        val videoList = response.body()
                        val adapter = VideoAdapter(
                            videoList!!,
                            LayoutInflater.from(this@MyTubeActivity),
                            Glide.with(this@MyTubeActivity),
                            this@MyTubeActivity
                        )
                        video_list.adapter = adapter
                    }
                }
            }
        )
    }
}

class VideoAdapter(
    var videoList: ArrayList<Video>,
    val inflater: LayoutInflater,
    val glide: RequestManager,
    val activity: Activity
) : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.myTube_title)
        val thumbnail: ImageView = itemView.findViewById(R.id.myTube_thumbnail)
        val content: TextView = itemView.findViewById(R.id.myTube_content)

        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                val intent = Intent(activity, MyTubeDetailActivity::class.java)
                intent.putExtra("video_url", videoList[position].video)
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.video_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = videoList[position].title
        holder.content.text = videoList[position].content
        glide.load(videoList[position].thumbnail).into(holder.thumbnail)
    }
}