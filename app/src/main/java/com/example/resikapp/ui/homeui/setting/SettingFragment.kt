package com.example.resikapp.ui.homeui.setting

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.auth0.android.jwt.JWT
import com.example.resikapp.data.response.UserItem
import com.example.resikapp.databinding.FragmentSettingBinding
import com.example.resikapp.helper.sharedpreferencetoken
import com.example.resikapp.ui.homeui.HomeActivity
import com.example.resikapp.ui.login.LoginActivity

class SettingFragment : Fragment() {
    private lateinit var sharedpreferencetoken: sharedpreferencetoken

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

        sharedpreferencetoken = sharedpreferencetoken(requireActivity())

        val token = sharedpreferencetoken.getToken()

        val notificationsViewModel = ViewModelProvider(this)[SettingViewModel::class.java]

        if (token!= null){
            val jwt = JWT(token)
            val allClaims = jwt.claims
            for ((key, value) in allClaims) {
                Log.d("SettingFragment", "Claim: $key = ${value.asString()}")
            }
            val username = jwt.getClaim("username").asString()

            username?.let {
                notificationsViewModel.showUser(requireContext(), it)

            }
        }

        binding.btnLogout.setOnClickListener {
            sharedpreferencetoken.clearData()
            startActivity(Intent(activity,LoginActivity::class.java))
            requireActivity().finishAffinity()
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

    private fun setDetailUser(detailUser: UserItem) {
        binding.tvEmail.text = detailUser.email
        binding.tvName.text = detailUser.username
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar2.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    
}
