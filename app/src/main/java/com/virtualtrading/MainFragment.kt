package com.virtualtrading

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.virtualtrading.databinding.FragmentMainBinding


class MainFragment : Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        val bottomSheetBehavior: BottomSheetBehavior<LinearLayout> = setupBottomSheet()
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
                Log.d("MainActivity", "Actions: Buy button clicked ")
            }
            actionSellCoin.setOnClickListener {
                Log.d("MainActivity", "Actions: Sell button clicked ")
            }

            actionConvertCoin.setOnClickListener {
                Log.d("MainActivity", "Actions: Convert button clicked ")
            }

            actionEarnCoin.setOnClickListener {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

