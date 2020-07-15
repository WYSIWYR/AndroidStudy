package com.example.project01

import android.app.Activity
import android.content.Context
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_sign_up)
        SetListener()
    }

    fun InitView(activity: Activity) {
        userIdView = activity.findViewById(R.id.userId)
        userPwdView = activity.findViewById(R.id.userPwd)
        userPwdConfirmView = activity.findViewById(R.id.userPwdConfirm)
        signUpBtn = activity.findViewById(R.id.signUp)
    }

    fun GetUserId(): String {
        return userIdView.text.toString()
    }

    fun GetUserPwd(): String {
        return userPwdView.text.toString()
    }

    fun GetUserPwdConfirm(): String {
        return userPwdConfirmView.text.toString()
    }

    fun SetListener() {
        signUpBtn.setOnClickListener {
            SignUp()
        }
    }

    fun SignUp(activity: Activity) {
        val userId = userIdView.text.toString()
        val userPwd = userPwdView.text.toString()
        val userPwdConfirm = userPwdConfirmView.text.toString()
        val signUp = SignUp(userId, userPwd, userPwdConfirm)

        (application as MasterApplication).service.signUp(signUp).enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(activity, "회원 가입에 실패 했습니다.", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    Toast.makeText(activity, "회원 가입에 성공 했습니다.", Toast.LENGTH_LONG).show()
                    val user = response.body()
                    val token = user!!.token!!
                    SaveUserToken(token, activity)
                }
            }
        })
    }

    fun SaveUserToken(token: String, activity: Activity) {
        val sp = activity.getSharedPreferences("SignIn", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("IsSignIn", token)
        editor.apply()
    }
}