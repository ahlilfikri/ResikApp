package com.example.resikapp.ui.homeui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.auth0.android.jwt.JWT
import com.example.resikapp.R
import com.example.resikapp.databinding.FragmentHomeBinding
import com.example.resikapp.ui.education.EducationActivity
import com.example.resikapp.ui.homeui.recycling.RecyclingFragment
import com.example.resikapp.ui.map.MapsActivity
import com.example.resikapp.ui.pickUpWorker.PickUpWorkerActivity
import com.example.resikapp.ui.pickupUser.PickupUserActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.text.observe(viewLifecycleOwner) {
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)
        Log.d("TOken", ": ${token}")

        if (token != null) {
            val jwt = JWT(token)
            val userType = jwt.getClaim("type").asString()
            Log.d("TYPEUSER", "Decoded ID from JWT: $userType")

            if (userType == "mitra") {
                binding.pickupWorker.visibility = View.VISIBLE
                binding.pickup.visibility = View.GONE
            } else if (userType == "client") {
                binding.pickup.visibility = View.VISIBLE
                binding.pickupWorker.visibility = View.GONE
            }
        }

        binding.education.setOnClickListener{
            startActivity(Intent(activity, EducationActivity::class.java))
        }
        binding.pickup.setOnClickListener{
            startActivity(Intent(activity, PickupUserActivity::class.java))
        }
        binding.recycling.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_home_to_navigation_recycling)
        }
        binding.map.setOnClickListener{
            startActivity(Intent(activity, MapsActivity::class.java))
        }
        binding.recyclingButton.setOnClickListener{
            findNavController().navigate(R.id.action_navigation_home_to_navigation_recycling)
        }
        binding.pickupWorker.setOnClickListener{
            startActivity(Intent(activity, PickUpWorkerActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
