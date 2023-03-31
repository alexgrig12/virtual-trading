package virtualtrading.coinactions

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import virtualtrading.coinactions.databinding.FragmentConvertCoinBinding

class ConvertCoinFragment : Fragment(R.layout.fragment_convert_coin) {
    private var _binding: FragmentConvertCoinBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentConvertCoinBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}