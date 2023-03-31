package virtualtrading.coinactions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import virtualtrading.coinactions.databinding.FragmentSellCoinBinding

class SellCoinFragment : Fragment(R.layout.fragment_sell_coin) {
    private var _binding: FragmentSellCoinBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSellCoinBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}