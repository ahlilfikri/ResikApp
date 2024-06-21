package com.example.resikapp.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class UserResponse(
	@field:SerializedName("data")
	val data: DataUser,

	@field:SerializedName("status")
	val status: String
)

data class data(
	@field:SerializedName("users")
	val listUser: List<User>? = null
)

@Parcelize
data class User(
	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("nomorTelepon")
	val nomorTelepon: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
): Parcelable

