package com.example.resikapp.ui.homeui.setting

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.resikapp.data.response.User
import com.example.resikapp.data.response.UserResponse
import com.example.resikapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingViewModel : ViewModel() {

    private val _userDetail = MutableLiveData<User?>()
    val userDetail: LiveData<User?> = _userDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun showUser(context: Context, id: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService(context).detailUser(id)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _userDetail.value = responseBody.data?.user
                    } else {
                        Log.e("SettingViewModel", "Response failed: ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("SettingViewModel", "Request failed: ${t.message}")
            }
        })
    }
}
