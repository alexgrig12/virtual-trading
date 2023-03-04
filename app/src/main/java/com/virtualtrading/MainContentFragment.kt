package com.virtualtrading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.virtualtrading.databinding.FragmentMainContentBinding

class MainContentFragment : Fragment(R.layout.fragment_main_content) {

    private var _binding: FragmentMainContentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signOut.setOnClickListener {
            findNavController().navigate(MainContentFragmentDirections.actionMainContentFragmentToGoogleAuthFragment())
            Firebase.auth.signOut()
        }
    }
}