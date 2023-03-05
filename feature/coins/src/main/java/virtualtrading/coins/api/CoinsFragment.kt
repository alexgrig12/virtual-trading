package virtualtrading.coins.api

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import virtualtrading.coins.R
import virtualtrading.coins.databinding.FragmentCoinsBinding

class CoinsFragment : Fragment(R.layout.fragment_coins) {
    private var _binding: FragmentCoinsBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentCoinsBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}