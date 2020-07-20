package com.example.project01

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_in)

        signUp.setOnClickListener {
            val intent = Intent(this@SignInActivity, EmailSignUpActivity::class.java)
            startActivity(intent)
        }

        signIn.setOnClickListener {
            val userId = userId.text.toString()
            val userPwd = userPwd.text.toString()
            (application as MasterApplication).service.signIn(
                userId, userPwd
            ).enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {

                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        val user = response.body()
                        val token = user!!.token!!
                        saveUserToken(token, this@SignInActivity)
                        (application as MasterApplication).createRetrofit()

                        Toast.makeText(this@SignInActivity, "로그인에 성공했습니다!", Toast.LENGTH_LONG)
                            .show()
                        startActivity(
                            Intent(this@SignInActivity, PostListActivity::class.java)
                        )
                    }
                }
            })
        }
    }

    fun saveUserToken(token: String, activity: Activity) {
        val sp = activity.getSharedPreferences("SignIn", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("IsSignIn", token)
        editor.apply()
    }
}