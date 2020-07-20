package com.example.project01

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_upload_post.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class UploadPostActivity : AppCompatActivity() {
    lateinit var imagePath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_post)

        choose_image.setOnClickListener {
            choosePicture()
        }

        post_upload.setOnClickListener {
            if (::imagePath.isInitialized) {
                uploadPost()
            }
        }

        post_list.setOnClickListener {
            startActivity(Intent(this@UploadPostActivity, PostListActivity::class.java))
        }

        my_post.setOnClickListener {
            startActivity(Intent(this@UploadPostActivity, MyPostActivity::class.java))
        }

        my_info.setOnClickListener {
            startActivity(Intent(this@UploadPostActivity, MyInfoActivity::class.java))
        }
    }

    fun choosePicture() {
        val hasCameraPermission = ContextCompat.checkSelfPermission(
            this@UploadPostActivity,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        )

        if (hasCameraPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this@UploadPostActivity,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                1000
            )
        } else {
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
            startActivityForResult(intent, 1000)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            val uri: Uri = data!!.data!!
            imagePath = getImagePath(uri)
        }
    }

    fun getImagePath(imageUri: Uri): String {
        lateinit var imagePath: String
        var columnIndex = 0
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        /*
         contentResolver는 content를 관리한다.
         imageUri는 상대 경로이기 때문에 절대 경로를 가져오기 위해 query를 사용한다.
         */
        val cursor = contentResolver.query(imageUri, projection, null, null, null)
        if (cursor!!.moveToFirst()) {
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }

        imagePath = cursor.getString(columnIndex)

        cursor.close()
        return imagePath
    }

    /*
    image 경로를 가져와서 이미지에 해당하는 RequestBody를 생성하고

     */
    fun uploadPost() {
        val file = File(imagePath)
        val fileRequestBody = RequestBody.create(MediaType.parse("image/*"), file)
        val part = MultipartBody.Part.createFormData("image", file.name, fileRequestBody)
        val content = RequestBody.create(MediaType.parse("text/plain"), getContent())
        (application as MasterApplication).service.uploadPost(part, content).enqueue(
            object : Callback<Post> {
                override fun onFailure(call: Call<Post>, t: Throwable) {
                    Log.d("Error", "Errot: $t")
                }

                override fun onResponse(call: Call<Post>, response: Response<Post>) {
                    if (response.isSuccessful) {
                        finish()
                        startActivity(
                            Intent(this@UploadPostActivity, MyPostActivity::class.java)
                        )
                    }
                }
            }
        )
    }

    fun getContent(): String {
        return content_input.text.toString()
    }
}