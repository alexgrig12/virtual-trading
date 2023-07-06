package virtualtrading.searchcoin.api

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.Lazy
import virtualtrading.searchcoin.R
import virtualtrading.searchcoin.databinding.FragmentSearchCoinBinding
import virtualtrading.searchcoin.internal.di.SearchCoinComponentViewModel
import virtualtrading.searchcoin.internal.di.SearchCoinViewModel
import javax.inject.Inject

class SearchCoinFragment : Fragment(R.layout.fragment_search_coin) {
    private var _binding: FragmentSearchCoinBinding? = null
    private val binding get() = _binding!!

    @Inject
    internal lateinit var searchCoinViewModelFactory: Lazy<SearchCoinViewModel.Factory>

    private val searchCoinViewModel: SearchCoinViewModel by activityViewModels { searchCoinViewModelFactory.get() }

    private val componentViewModel: SearchCoinComponentViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        componentViewModel.searchCoinComponent.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSearchCoinBinding.bind(view)
        binding.baseText.text = searchCoinViewModel.baseText
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}