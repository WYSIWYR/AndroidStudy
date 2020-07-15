package com.example.project01

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService{
    //GET으로 가져온 데이터를 Call안에 선언한 타입으로 사용한다.
    @GET("json/students/")
    fun getStudentList(): Call<ArrayList<Student>>

    @POST("json/students/")
    fun createStudent(
        @Body student: Student
    ): Call<Student>

    @POST("user/signup/")
    fun signUp(
        @Body signUp: SignUp
    ): Call<User>
}