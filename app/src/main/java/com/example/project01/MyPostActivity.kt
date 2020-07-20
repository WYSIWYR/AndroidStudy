package com.example.project01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import kotlinx.android.synthetic.main.activity_my_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_post)
        createList()

        post_list.setOnClickListener {
            startActivity(Intent(this@MyPostActivity, PostListActivity::class.java))
        }

        upload_post.setOnClickListener {
            startActivity(Intent(this@MyPostActivity, UploadPostActivity::class.java))
        }

        my_info.setOnClickListener {
            startActivity(Intent(this@MyPostActivity, MyInfoActivity::class.java))
        }
    }

    fun createList() {
        (application as MasterApplication).service.getMyPostList().enqueue(
            object : Callback<ArrayList<Post>> {
                override fun onFailure(call: Call<ArrayList<Post>>, t: Throwable) {
                    Log.d("Retrofit", "Error")
                }

                override fun onResponse(
                    call: Call<ArrayList<Post>>,
                    response: Response<ArrayList<Post>>
                ) {
                    if (response.isSuccessful) {
                        val postList = response.body()
                        val adapter = MyPostAdapter(
                            postList!!,
                            LayoutInflater.from(this@MyPostActivity),
                            Glide.with(this@MyPostActivity)
                        )

                        my_post_recycler_view.adapter = adapter
                        my_post_recycler_view.layoutManager =
                            LinearLayoutManager(this@MyPostActivity)
                    }
                }
            }
        )
    }
}

class MyPostAdapter(
    var postList: ArrayList<Post>,
    val inflater: LayoutInflater,
    val glide: RequestManager
) : RecyclerView.Adapter<MyPostAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postOwner: TextView = itemView.findViewById(R.id.post_owner)
        val postImage: ImageView = itemView.findViewById(R.id.post_img)
        val postContent: TextView = itemView.findViewById(R.id.post_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.teststagram_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.postOwner.text = postList[position].owner
        holder.postContent.text = postList[position].content
        glide.load(postList[position].image).into(holder.postImage)
    }
}