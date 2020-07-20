package com.example.project01

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_my_info.*

class MyInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_info)

        logout.setOnClickListener {
            val sp = getSharedPreferences("SignIn", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString("IsSignIn", "null")
            editor.apply()
            (application as MasterApplication).createRetrofit()
            finish()
            startActivity(
                Intent(this@MyInfoActivity, SignInActivity::class.java)
            )
        }

        post_list.setOnClickListener {
            startActivity(Intent(this@MyInfoActivity, PostListActivity::class.java))
        }

        my_post.setOnClickListener {
            startActivity(Intent(this@MyInfoActivity, MyPostActivity::class.java))
        }

        upload_post.setOnClickListener {
            startActivity(Intent(this@MyInfoActivity, UploadPostActivity::class.java))
        }
    }
}