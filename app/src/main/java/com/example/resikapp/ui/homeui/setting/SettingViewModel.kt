package com.example.resikapp.ui.homeui.setting

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.auth0.android.jwt.JWT
import com.example.resikapp.data.response.User
import com.example.resikapp.data.response.UserResponse
import com.example.resikapp.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SettingViewModel : ViewModel() {

    private val _userDetail = MutableLiveData<User?>()
    val userDetail : MutableLiveData<User?> = _userDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading



    fun showUser( id: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().detailUser(id)
        client.enqueue(/* callback = */ object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _userDetail.value = responseBody.data?.user

                    } else {
                        Log.e("FragmentDetail", "response gagal : ${response.message()}")
                    }
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("FragmentDetail", "gagal: ${t.message}")
            }

        })
    }
}
