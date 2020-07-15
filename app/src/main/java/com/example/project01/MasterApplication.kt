package com.example.project01

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MasterApplication : Application() {

    lateinit var service: RetrofitService

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        createRetrofit()
    }

    fun createRetrofit() {
        val header = Interceptor {
            val original = it.request()
            if (checkSignIn()) {
                getUserToken().let {token ->
                    val request = original.newBuilder()
                        .header("Authorization","token $token")
                        .build()
                    it.proceed(request)
                }
            } else {
                it.proceed(original)
            }
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(header)
            .addNetworkInterceptor(StethoInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        service = retrofit.create(RetrofitService::class.java)
    }

    fun checkSignIn(): Boolean {
        val sp = getSharedPreferences("SignIn", Context.MODE_PRIVATE)
        val token = sp.getString("IsSingIn", "null")
        return token != "null"
    }

    fun getUserToken(): String? {
        val sp = getSharedPreferences("SignIn", Context.MODE_PRIVATE)
        val token = sp.getString("IsSingIn", "null")
        return if (token == "null") null
        else token
    }
}