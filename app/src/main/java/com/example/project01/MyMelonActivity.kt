package com.example.project01

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_my_melon.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MyMelonActivity : AppCompatActivity() {
    var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_melon)

        (application as MasterApplication).service.getMusicList().enqueue(
            object : Callback<ArrayList<Music>> {
                override fun onFailure(call: Call<ArrayList<Music>>, t: Throwable) {
                    Log.d("Error", "Error: $t")
                }

                override fun onResponse(
                    call: Call<ArrayList<Music>>,
                    response: Response<ArrayList<Music>>
                ) {
                    if (response.isSuccessful) {
                        val musicList = response.body()
                        val adapter = MusicAdapter(
                            musicList!!,
                            LayoutInflater.from(this@MyMelonActivity),
                            Glide.with(this@MyMelonActivity),
                            this@MyMelonActivity
                        )
                        music_list.adapter = adapter
                    }
                }
            }
        )
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.stop()
        mediaPlayer?.release()
    }

    inner class MusicAdapter(
        var musicList: ArrayList<Music>,
        val inflater: LayoutInflater,
        val glide: RequestManager,
        val activity: Activity
    ) : RecyclerView.Adapter<MusicAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.findViewById(R.id.myMelon_title)
            val thumbnail: ImageView = itemView.findViewById(R.id.myMelon_thumbnail)
            val play: ImageView = itemView.findViewById(R.id.myMelon_play)

            init {
                itemView.setOnClickListener {
                    val position: Int = adapterPosition
                    val path = musicList[position].song
                    try {
                        mediaPlayer?.stop()
                        mediaPlayer?.release()
                        mediaPlayer = null
                        mediaPlayer = MediaPlayer.create(this@MyMelonActivity, Uri.parse(path))
                        mediaPlayer?.start()
                    } catch (e: Exception) {
                        Log.d("Error", "Error: $e")
                    }
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = inflater.inflate(R.layout.music_item, parent, false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int {
            return musicList.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.title.text = musicList[position].title
            glide.load(musicList[position].thumbnail).into(holder.thumbnail)
        }
    }
}
