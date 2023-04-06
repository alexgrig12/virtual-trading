package virtualtrading.coindetails.api

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import virtualtrading.coindetails.R
import virtualtrading.coindetails.databinding.FragmentCoinDetailsBinding

class CoinDetailsFragment() : Fragment(R.layout.fragment_coin_details) {
    private var _binding: FragmentCoinDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCoinDetailsBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}