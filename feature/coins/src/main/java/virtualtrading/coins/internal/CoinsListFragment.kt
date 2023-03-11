package virtualtrading.coins.internal

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import virtualtrading.coins.R
import virtualtrading.coins.databinding.FragmentCoinsListBinding

internal class CoinsListFragment : Fragment(R.layout.fragment_coins_list) {
    private var _binding: FragmentCoinsListBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCoinsListBinding.bind(view)
        setupFilterTabs()
    }

    private fun setupFilterTabs() {
        val filterTabs = binding.filterTabLayout
        filterTabs.addTab(filterTabs.newTab().setText("All"))
        filterTabs.addTab(filterTabs.newTab().setText("Price"))
        filterTabs.addTab(filterTabs.newTab().setText("24h Change"))
        filterTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (filterTabs.selectedTabPosition) {
                    0 -> Toast.makeText(requireContext(), "All tab selected", Toast.LENGTH_SHORT).show()
                    1 -> Toast.makeText(requireContext(), "Price tab selected", Toast.LENGTH_SHORT).show()
                    2 -> Toast.makeText(requireContext(), "24h change tab selected", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
                return
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
                return
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}