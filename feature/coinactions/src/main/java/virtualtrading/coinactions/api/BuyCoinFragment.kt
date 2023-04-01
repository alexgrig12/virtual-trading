package virtualtrading.coinactions.api

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import virtualtrading.coinactions.R
import virtualtrading.coinactions.databinding.FragmentBuyCoinBinding

class BuyCoinFragment : Fragment(R.layout.fragment_buy_coin) {
    private var _binding: FragmentBuyCoinBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentBuyCoinBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}