package com.example.resikapp.data.retrofit

import com.example.resikapp.data.response.ApiResponseDetailEducation
import com.example.resikapp.data.response.ApiResponseEducation
import com.example.resikapp.data.response.ApiResponsePesanan
import com.example.resikapp.data.response.ApiResponsePesananOnComing
import com.example.resikapp.data.response.ApiResponseUser
import com.example.resikapp.data.response.CreatePesananRequest
import com.example.resikapp.data.response.CreatePesananResponse
import com.example.resikapp.data.response.LoginRequest
import com.example.resikapp.data.response.LoginResponse
import com.example.resikapp.data.response.UpdatePesananRequest
import com.example.resikapp.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @GET("articles")
    fun getArticles(): Call<ApiResponseEducation>

    @GET("article/{id}")
    fun getDetailArticle(@Path("id") username: String): Call<ApiResponseDetailEducation>

    @GET("pesanan/history")
    fun getPesanan(): Call<ApiResponsePesanan>

    @GET("daftar/pesanan")
    fun getDaftarPesanan(): Call<ApiResponsePesananOnComing>

    @POST("pesanan")
    fun createPesanan(
        @Body request: CreatePesananRequest): Call<CreatePesananResponse>

    @PUT("pesanan/{id}")
    fun updatePesanan(@Path("id") id: String, @Body request: UpdatePesananRequest): Call<CreatePesananResponse>

    @GET("users/{id}")
    fun detailUser(@Path("id") id: String): Call<UserResponse>

    @GET("users")
    fun getAllUsers(): Call<ApiResponseUser>
}
