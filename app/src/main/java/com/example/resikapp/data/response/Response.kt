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

data class ApiResponseUser(
    val status: String,
    val data: DataUser
)

data class ApiResponseDetailUser(
    val status: String,
    val data: DataDetailUser
)

data class DataUser(
    val listUser: List<UserItem>
)

data class DataDetailUser(
    val user: UserItem
)

@Parcelize
data class UserItem(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("nomorTelepon")
    val nomorTelepon: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("username")
    val username: String,


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


data class ApiResponsePesanan(
    val status: String,
    val data: DataPesanan
)

data class ApiResponseDetailPesanan(
    val status: String,
    val data: DataDetailPesanan
)

data class DataPesanan(
    val pesanan: List<HistoryPesananItem>
)

data class DataDetailPesanan(
    val pesanan: PesananItem
)

data class CreatePesananRequest(
    @field:SerializedName("alamat")
    val alamat: String
)

data class CreatePesananResponse(
    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: PesananData
)

data class PesananData(
    @field:SerializedName("pesananId")
    val pesananId: String
)


@Parcelize
data class HistoryPesananItem(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("userId")
    val user: String,

    @field:SerializedName("alamat")
    val alamat: String,

    @field:SerializedName("harga")
    val harga: String,

    @field:SerializedName("berat")
    val berat: String,

    @field:SerializedName("idMitra")
    val mitra: String,

    @field:SerializedName("createdAt")
    val created: String,

    ) : Parcelable
@Parcelize
data class PesananItem(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("userId")
    val user: String,

    @field:SerializedName("alamat")
    val alamat: String,

    @field:SerializedName("harga")
    val harga: String,

    @field:SerializedName("berat")
    val berat: String,

    @field:SerializedName("idMitra")
    val mitra: String,

    ) : Parcelable
