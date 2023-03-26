package virtualtrading.coins.internal

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import virtualtrading.coins.R
import virtualtrading.coins.databinding.FragmentFavoritesBinding
import virtualtrading.coins.internal.di.CoinsComponentViewModel
import javax.inject.Inject

internal class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var coinViewModelFactory: dagger.Lazy<CoinsViewModel.Factory>
    private val componentViewModel: CoinsComponentViewModel by viewModels()
    private val coinsViewModel: CoinsViewModel by activityViewModels { coinViewModelFactory.get() }

    private var adapter: ChooseFavoriteAdapter? = null

    private fun showNoFavorites() {
        binding.fragmentNoFavorites.root.visibility = View.VISIBLE
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.coinsComponent.inject(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoritesBinding.bind(view)
        showNoFavorites()
        this.adapter = ChooseFavoriteAdapter() { recommendedCoin ->
            coinsViewModel.toggleRecommended(recommendedCoin)
        }

        with(binding.fragmentNoFavorites.chooseRecommendedCoins) {
            this.adapter = this@FavoritesFragment.adapter
            itemAnimator = null
            addItemDecoration(ChooseFavoriteItemDecoration(4, outerTopOffset = 32))
            setHasFixedSize(true)
            overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
        coinsViewModel.recommendedToBeFavorite.observe(viewLifecycleOwner) { recommendedCoins ->
            adapter?.submitList(recommendedCoins)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        adapter = null
    }
}