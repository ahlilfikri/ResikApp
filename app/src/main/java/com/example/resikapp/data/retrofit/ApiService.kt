package com.example.resikapp.data.retrofit

import com.example.resikapp.data.response.ApiResponseDetailEducation
import com.example.resikapp.data.response.ApiResponseEducation
import com.example.resikapp.data.response.LoginRequest
import com.example.resikapp.data.response.LoginResponse
import com.example.resikapp.data.response.UserItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>
    @GET("users")
    fun getUsers(): Call<List<UserItem>>

    @GET("articles")
    fun getArticles(): Call<ApiResponseEducation>

    @GET("article/{id}")
    fun getDetailArticle(@Path("id") username: String): Call<ApiResponseDetailEducation>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<UserItem>>

    @GET("users/{username}/following")
    fun getFollowings(@Path("username") username: String): Call<List<UserItem>>

    @GET("users/{username}")
    fun detailUser(@Path("username") username: String): Call<UserItem>
}
