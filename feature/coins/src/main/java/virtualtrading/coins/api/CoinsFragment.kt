package virtualtrading.coins.api

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.Lazy
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import virtualtrading.coins.R
import virtualtrading.coins.databinding.FragmentCoinsBinding
import virtualtrading.coins.internal.CoinsComponentViewModel
import virtualtrading.coins.internal.CoinsViewModel
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
        coinsViewModel.coins.onEach {
            Log.d("TAG", "${it.toString()} ")
        }.launchIn(lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}