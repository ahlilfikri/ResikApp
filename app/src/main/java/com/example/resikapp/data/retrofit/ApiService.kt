package com.example.resikapp.data.retrofit

import com.example.resikapp.data.response.ApiResponseDetailEducation
import com.example.resikapp.data.response.ApiResponseDetailPesanan
import com.example.resikapp.data.response.ApiResponseDetailUser
import com.example.resikapp.data.response.ApiResponseEducation
import com.example.resikapp.data.response.ApiResponsePesanan
import com.example.resikapp.data.response.ApiResponseUser
import com.example.resikapp.data.response.CreatePesananRequest
import com.example.resikapp.data.response.CreatePesananResponse
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
    fun getUsers(): Call<ApiResponseUser>

    @GET("user/{id}")
    fun getDetailUser(@Path("id") username: String): Call<ApiResponseDetailUser>

    @GET("articles")
    fun getArticles(): Call<ApiResponseEducation>

    @GET("article/{id}")
    fun getDetailArticle(@Path("id") username: String): Call<ApiResponseDetailEducation>

    @GET("pesanan/history")
    fun getPesanan(): Call<ApiResponsePesanan>

    @POST("pesanan")
    fun createPesanan(@Body request: CreatePesananRequest): Call<CreatePesananResponse>


}
