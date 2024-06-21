package com.example.resikapp.ui.homeui.setting

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.resikapp.data.response.UserItem
import com.example.resikapp.data.response.ApiResponseUser
import com.example.resikapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingViewModel : ViewModel() {

    private val _userDetail = MutableLiveData<UserItem?>()
    val userDetail: LiveData<UserItem?> = _userDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun showUser(context: Context, username: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService(context).getAllUsers()
        client.enqueue(object : Callback<ApiResponseUser> {
            override fun onResponse(call: Call<ApiResponseUser>, response: Response<ApiResponseUser>) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    val users = response.body()?.data?.listUser

                    if (users != null) {
                        val user = users.find { it.username == username }
                        _userDetail.value = user
                    } else {
                        Log.e("SettingViewModel", "No users found in response")
                    }
                } else {
                    Log.e("SettingViewModel", "Response failed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ApiResponseUser>, t: Throwable) {
                _isLoading.value = false
                Log.e("SettingViewModel", "Request failed: ${t.message}")
            }
        })
    }
}
