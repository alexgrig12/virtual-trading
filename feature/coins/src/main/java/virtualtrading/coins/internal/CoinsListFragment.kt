package virtualtrading.coins.internal

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import dagger.Lazy
import virtualtrading.coins.R
import virtualtrading.coins.databinding.FragmentCoinsListBinding
import virtualtrading.coins.internal.di.CoinsComponentViewModel
import javax.inject.Inject

internal class CoinsListFragment : Fragment(R.layout.fragment_coins_list) {
    private var _binding: FragmentCoinsListBinding? = null
    private val binding get() = _binding!!

    @Inject
    internal lateinit var coinViewModelFactory: Lazy<CoinsViewModel.Factory>
    private val coinsViewModel: CoinsViewModel by activityViewModels { coinViewModelFactory.get() }
    private val componentViewModel: CoinsComponentViewModel by viewModels()

    private var adapter: CoinAdapter? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.coinsComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCoinsListBinding.bind(view)
        this.adapter = CoinAdapter()
        binding.coinList.apply {
            adapter = this@CoinsListFragment.adapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        }

        coinsViewModel.coins.observe(viewLifecycleOwner) { newCoinList ->
            adapter?.submitList(newCoinList)
        }

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
        adapter = null
    }
}