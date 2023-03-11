package virtualtrading.coins.internal

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import virtualtrading.coins.R
import virtualtrading.coins.databinding.FragmentFavoritesBinding

internal class FavoritesFragment : Fragment(R.layout.fragment_favorites) {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoritesBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}