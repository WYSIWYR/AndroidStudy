package com.example.project01

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService{
    //GET으로 가져온 데이터를 Call안에 선언한 타입으로 사용한다.
    @GET("json/students/")
    fun getStudentList(): Call<ArrayList<Student>>

    @POST("json/students/")
    fun createStudent(
        @Body student: Student
    ): Call<Student>

    @POST("user/signup/")
    @FormUrlEncoded
    fun signUp(
        @Field("username") username: String,
        @Field("password1") password1: String,
        @Field("password2") password2: String
    ): Call<User>

    @POST("user/login/")
    @FormUrlEncoded
    fun signIn(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<User>

    @GET("instagram/post/list/all")
    fun getAllPosts(): Call<ArrayList<Post>>

    @Multipart
    @POST("instagram/post/")
    fun uploadPost(
        @Part iamge: MultipartBody.Part,
        @Part ("content")requestBody: RequestBody
    ): Call<Post>

    @GET("instagram/post/list")
    fun getMyPostList(): Call<ArrayList<Post>>

    @GET("youtube/list")
    fun getVideoList(): Call<ArrayList<Video>>

    @GET("melon/list")
    fun getMusicList(): Call<ArrayList<Music>>
}