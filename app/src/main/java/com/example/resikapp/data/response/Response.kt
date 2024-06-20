package com.example.resikapp.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize



data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val status: String,
    val message: String,
    val data: LoginData?
)

data class LoginData(
    val token: String
)

@Parcelize
data class UserItem(

    @field:SerializedName("avatar_url")
    val avatarUrl: String,

    @field:SerializedName("following_url")
    val followingUrl: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("followers_url")
    val followersUrl: String,

    @field:SerializedName("type")
    val type: String,
) : Parcelable


data class ApiResponseEducation(
    val status: String,
    val data: DataEducation
)

data class ApiResponseDetailEducation(
    val status: String,
    val data: DataDetailEducation
)

data class DataEducation(
    val listArtikel: List<ArticleItem>
)

data class DataDetailEducation(
    val artikel: ArticleItem
)
@Parcelize
data class ArticleItem(

    @field:SerializedName("penulis")
    val penulis: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("judul")
    val judul: String,

    @field:SerializedName("isi")
    val isi: String,

) : Parcelable