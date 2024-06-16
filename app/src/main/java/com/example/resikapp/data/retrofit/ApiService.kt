package com.example.resikapp.data.retrofit

import com.example.resikapp.data.response.UserItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    fun getUsers(): Call<List<UserItem>>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<UserItem>>

    @GET("users/{username}/following")
    fun getFollowings(@Path("username") username: String): Call<List<UserItem>>

    @GET("users/{username}")
    fun detailUser(@Path("username") username: String): Call<UserItem>
}
