package com.virtualtrading

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.virtualtrading.databinding.FragmentMainBinding
import kotlinx.coroutines.Dispatchers
import virtualtrading.coinactions.BuyCoinFragment
import virtualtrading.data.firestore.api.datastore.UserPreferences


class MainFragment : Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var userPreferences: UserPreferences

    override fun onAttach(context: Context) {
        super.onAttach(context)
        userPreferences = UserPreferences.getInstance(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)

        bottomSheetBehavior = setupBottomSheet()
        setupBottomNavigation(bottomSheetBehavior)
        setupActions()
    }

    private fun setupBottomSheet(): BottomSheetBehavior<LinearLayout> {
        val bottomSheetBehavior: BottomSheetBehavior<LinearLayout> = BottomSheetBehavior.from(binding.bottomSheet.root)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        return bottomSheetBehavior
    }

    private fun setupBottomNavigation(bottomSheetBehavior: BottomSheetBehavior<LinearLayout>) {
        val navView = binding.mainNavView
        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragment_main_container) as NavHostFragment
        val navController = navHostFragment.navController
        navView.setupWithNavController(navController)

        navView.setOnItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.navigation_actions -> {
                    handleActionsBottomSheet(bottomSheetBehavior)
                    false
                }
                else -> {
                    navController.navigate(
                        menuItem.itemId, null, NavOptions.Builder().setPopUpTo(navController.graph.id, true).build()
                    )
                    true
                }
            }
        }
    }

    private fun setupActions() {
        binding.bottomSheet.apply {
            actionBuyCoin.setOnClickListener {
                val request = NavDeepLinkRequest.Builder
                    .fromUri("feature:coinactions://virtualtrading.coinactions/buyCoinFragment".toUri())
                    .build()
                findNavController().navigate(request)
            }
            actionSellCoin.setOnClickListener {
                val request = NavDeepLinkRequest.Builder
                    .fromUri("feature:coinactions://virtualtrading.coinactions/sellCoinFragment".toUri())
                    .build()
                findNavController().navigate(request)
            }
            actionConvertCoin.setOnClickListener {
                val request = NavDeepLinkRequest.Builder
                    .fromUri("feature:coinactions://virtualtrading.coinactions/convertCoinFragment".toUri())
                    .build()
                findNavController().navigate(request)
            }
            actionEarnCoin.setOnClickListener {
                handleActionsBottomSheet(bottomSheetBehavior)
                Log.d("MainActivity", "Actions: Earn button clicked ")
            }
        }
    }

    private fun handleActionsBottomSheet(bottomSheetBehavior: BottomSheetBehavior<out ViewGroup>) {
        if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_HIDDEN) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            binding.mainNavView.menu.apply {
                getItem(0).isVisible = false
                getItem(2).isVisible = false
                getItem(1).icon = AppCompatResources.getDrawable(requireContext(), R.drawable.cancel)
            }
        } else if (bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            binding.mainNavView.menu.apply {
                getItem(0).isVisible = true
                getItem(2).isVisible = true
                getItem(1).icon = AppCompatResources.getDrawable(requireContext(), R.drawable.main_actions)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

