package com.example.project01

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmailSignUpActivity : AppCompatActivity() {
    lateinit var userIdView: EditText
    lateinit var userPwdView: EditText
    lateinit var userPwdConfirmView: EditText
    lateinit var signUpBtn: Button
    lateinit var signInBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if ((application as MasterApplication).checkSignIn()) {
            startActivity(
                Intent(this@EmailSignUpActivity, PostListActivity::class.java)
            )
        } else {
            setContentView(R.layout.activity_email_sign_up)
            initView(this@EmailSignUpActivity)
            setListener(this@EmailSignUpActivity)
        }
        //chrome://inspect/#devices
    }

    fun initView(activity: Activity) {
        userIdView = activity.findViewById(R.id.userId)
        userPwdView = activity.findViewById(R.id.userPwd)
        userPwdConfirmView = activity.findViewById(R.id.userPwdConfirm)
        signUpBtn = activity.findViewById(R.id.signUp)
        signInBtn = activity.findViewById(R.id.signIn)
    }

    fun getUserId(): String {
        return userIdView.text.toString()
    }

    fun getUserPwd(): String {
        return userPwdView.text.toString()
    }

    fun getUserPwdConfirm(): String {
        return userPwdConfirmView.text.toString()
    }

    fun setListener(activity: Activity) {
        signUpBtn.setOnClickListener {
            signUp(activity)
        }
        signInBtn.setOnClickListener {
            startActivity(
                Intent(this@EmailSignUpActivity, SignInActivity::class.java)
            )
        }
    }

    fun signUp(activity: Activity) {
        val userId = getUserId()
        val userPwd = getUserPwd()
        val userPwdConfirm = getUserPwdConfirm()

        (application as MasterApplication).service.signUp(userId, userPwd, userPwdConfirm).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(activity, "회원 가입에 실패 했습니다.", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    Toast.makeText(activity, "회원 가입에 성공 했습니다.", Toast.LENGTH_LONG).show()
                    val user = response.body()
                    val token = user!!.token!!
                    saveUserToken(token, activity)
                    (application as MasterApplication).createRetrofit()
                    activity.startActivity(
                        Intent(activity, PostListActivity::class.java)
                    )
                }
            }
        })
    }

    fun saveUserToken(token: String, activity: Activity) {
        val sp = activity.getSharedPreferences("SignIn", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("IsSignIn", token)
        editor.apply()
    }
}