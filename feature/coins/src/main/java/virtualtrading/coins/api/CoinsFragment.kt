package virtualtrading.coins.api

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.Lazy
import virtualtrading.coins.R
import virtualtrading.coins.databinding.FragmentCoinsBinding
import virtualtrading.coins.internal.CoinsListFragment
import virtualtrading.coins.internal.CoinsViewModel
import virtualtrading.coins.internal.FavoritesFragment
import virtualtrading.coins.internal.di.CoinsComponentViewModel
import javax.inject.Inject

class CoinsFragment : Fragment(R.layout.fragment_coins) {
    private var _binding: FragmentCoinsBinding? = null
    private val binding get() = _binding!!

    @Inject
    internal lateinit var coinViewModelFactory: Lazy<CoinsViewModel.Factory>
    private val coinsViewModel: CoinsViewModel by viewModels { coinViewModelFactory.get() }
    private val componentViewModel: CoinsComponentViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.coinsComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCoinsBinding.bind(view)
        setupTabsWithViewPager()
    }

    private fun setupTabsWithViewPager() {
        binding.viewPager.adapter = ListTabAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Coins"
                }
                1 -> {
                    tab.text = "Favorite"
                }
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class ListTabAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                CoinsListFragment()
            }
            1 -> {
                FavoritesFragment()
            }
            else -> {
                throw NotImplementedError()
            }
        }
    }
}