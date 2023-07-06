package virtualtrading.coindetails.api

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import virtualtrading.coindetails.R
import virtualtrading.coindetails.databinding.FragmentCoinDetailsBinding
import virtualtrading.coindetails.internal.CoinDetailsViewModel
import virtualtrading.coindetails.internal.di.CoinDetailsComponentViewModel
import javax.inject.Inject

class CoinDetailsFragment() : Fragment(R.layout.fragment_coin_details) {
    private var _binding: FragmentCoinDetailsBinding? = null
    private val binding get() = _binding!!

    @Inject
    internal lateinit var coinDetailsViewModelFactory: dagger.Lazy<CoinDetailsViewModel.Factory>
    private val coinDetailsViewModel: CoinDetailsViewModel by viewModels { coinDetailsViewModelFactory.get() }
    private val componentViewModel: CoinDetailsComponentViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.coinDetailsComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCoinDetailsBinding.bind(view)
        val coinId: String? = arguments?.getString("coinId")
        Log.d(TAG, "onViewCreated:${coinId}")
        coinId?.let { id -> coinDetailsViewModel.getCoinById(coidId = id) } ?: kotlin.run {
            Toast.makeText(requireContext(), "Some problems with coin", Toast.LENGTH_SHORT).show()
            navigateBack()
        }

        coinDetailsViewModel.currentCoin.observe(viewLifecycleOwner) { coin ->
            binding.tvRank.text = coin.rank.toString()
            binding.tvSymbol.text = coin.symbol
            binding.tvName.text = coin.name
            binding.tvPrice.text = getString(R.string.dollar_amount, coin.price)
            binding.tvMarketCap.text = getString(R.string.dollar_amount, coin.marketCap)
            binding.tvTotalSupply.text = coin.totalSupply
            binding.tvMaxSupply.text = coin.maxSupply
            binding.tvCirculatingSupply.text = coin.circulatingSupply
            binding.tvDescription.text = coin.description
        }




        binding.appbar.setNavigationOnClickListener {
            navigateBack()
        }
    }

    private fun navigateBack() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "CoinDetailsFragment"
    }
}