package virtualtrading.coindetails.api

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
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
    internal lateinit var coinDetailsViewModelFactory: CoinDetailsViewModel.Factory
    private val coinDetailsViewModel: CoinDetailsViewModel by viewModels()
    private val componentViewModel: CoinDetailsComponentViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.coinDetailsComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCoinDetailsBinding.bind(view)
        Log.d("TAG", "onViewCreated:${arguments?.getString("coinId")} ")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}