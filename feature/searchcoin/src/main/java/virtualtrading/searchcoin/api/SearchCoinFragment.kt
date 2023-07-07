package virtualtrading.searchcoin.api

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.Lazy
import virtualtrading.base.navigateBack
import virtualtrading.searchcoin.R
import virtualtrading.searchcoin.databinding.FragmentSearchCoinBinding
import virtualtrading.searchcoin.internal.SearchCoinViewModel
import virtualtrading.searchcoin.internal.di.SearchCoinComponentViewModel
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
        binding.search.apply {
            setOnQueryTextFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    val imm: InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.showSoftInput(v.findFocus(), 0)
                }
            }
            requestFocus()
        }
        binding.cancelSearch.setOnClickListener {
            requireActivity().navigateBack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}