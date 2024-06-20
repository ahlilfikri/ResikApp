package com.example.resikapp.ui.homeui.setting

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.auth0.android.jwt.JWT
import com.example.resikapp.data.response.User
import com.example.resikapp.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)
        Log.d("com.example.resikapp.ui.homeui.setting.SettingFragment", "Token from SharedPreferences: $token")

        val notificationsViewModel = ViewModelProvider(this)[SettingViewModel::class.java]

        if (token!= null){
            val jwt = JWT(token)
            val id = jwt.getClaim("id").asString()
            Log.d("ID Dari JWT", "Decoded ID from JWT: $id")

            notificationsViewModel.showUser(id?:"gg")
        }




        notificationsViewModel.userDetail.observe(viewLifecycleOwner) {
            if (it != null) {
                setDetailUser(it)
            }
        }

        notificationsViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setDetailUser(detailUser: User) {
        binding.tvEmail.text = detailUser.email
        binding.tvName.text = detailUser.username
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar2.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
