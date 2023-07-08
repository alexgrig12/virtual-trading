package virtualtrading.searchcoin.api

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.Lazy
import virtualtrading.base.extensions.findNavControllerById
import virtualtrading.base.extensions.navigateBack
import virtualtrading.base.ui.CoinAdapter
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
    private var adapter: CoinAdapter? = null

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
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    Log.d(TAG, "onQueryTextChange: $newText")
                    searchCoinViewModel.query = newText
                    return true
                }
            })
        }
        binding.cancelSearch.setOnClickListener {
            requireActivity().navigateBack()
        }
        this.adapter = CoinAdapter() { coinId: String ->
            Log.d(TAG, "onViewCreated: $coinId")
            this.findNavControllerById(virtualtrading.base.R.id.activity_base_fragment_container)
                .navigate(
                    virtualtrading.base.R.id.action_searchCoinFragment_to_coinDetailsFragment,
                    bundleOf("coinId" to coinId)
                )
        }.also { coinAdapter ->
            coinAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                override fun onChanged() {
                    binding.coinList.scrollToPosition(0)
                }

                override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                    binding.coinList.scrollToPosition(0)
                }

                override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
                    binding.coinList.scrollToPosition(0)
                }

                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    binding.coinList.scrollToPosition(0)
                }

                override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                    binding.coinList.scrollToPosition(0)
                }

                override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                    binding.coinList.scrollToPosition(0)
                }
            })
        }
        binding.coinList.apply {
            adapter = this@SearchCoinFragment.adapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }



        searchCoinViewModel.searchResult.observe(viewLifecycleOwner) { searchResult ->
            Log.d(TAG, "onViewCreated:searchResult coins: $searchResult ")
            adapter?.submitList(searchResult)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "SearchCoinFragment"
    }
}