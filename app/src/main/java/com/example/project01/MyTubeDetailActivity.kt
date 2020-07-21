package com.example.project01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_my_tube_detail.*

class MyTubeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_tube_detail)

        val url = intent.getStringExtra("video_url")
        /*
        MediaController를 생성해 간단한 동영상을 보여주는데 사용한다.
        동영상이나 음악을 본격적으로 제어를 하기위해서는 Exoplayer를 사용해야 한다.
         */
        val mediaController = MediaController(this@MyTubeDetailActivity)
        video_view.setVideoPath(url)
        video_view.requestFocus()
        video_view.start()
        mediaController.show()

    }
}