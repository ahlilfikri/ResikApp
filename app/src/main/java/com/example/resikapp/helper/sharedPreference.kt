package com.example.resikapp.helper

import android.content.Context
import android.content.SharedPreferences

class sharedpreferencetoken (context: Context) {
    private val sharedpreference : SharedPreferences = context.getSharedPreferences("MY_PREF", Context.MODE_PRIVATE)
    private val editor : SharedPreferences.Editor = sharedpreference.edit()

    fun saveToken(token : String){
        editor.putString("token", token)
        editor.apply()
    }

    fun getToken() : String?{
        return sharedpreference.getString("token", null)
    }

    fun getUsername() : String?{
        return sharedpreference.getString("name", null)
    }

    fun clearData(){
        editor.remove("token")
        editor.apply()
    }
}