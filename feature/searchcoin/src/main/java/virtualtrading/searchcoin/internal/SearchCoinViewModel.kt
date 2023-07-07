package virtualtrading.searchcoin.internal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import virtualtrading.coinranking.CoinrankingRepository
import javax.inject.Inject

internal class SearchCoinViewModel(private val coinrankingRepository: CoinrankingRepository) : ViewModel() {

    private val _query = MutableStateFlow("")
    var query: String
        get() = _query.value
        set(value) {
            _query.value = value
        }

    private val _searchResult = _query
        .debounce(500)
        .filter { currentQuery ->
            return@filter currentQuery.isNotEmpty()
        }
        .distinctUntilChanged()
        .map { currentQuery -> currentQuery.lowercase() }
        .mapLatest { currentQuery ->
            searchCoin(currentQuery)
        }
        .asLiveData()
    val searchResult: LiveData<String>
        get() = _searchResult


    private fun searchCoin(query: String): String {
        return query
    }


    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val coinrankingRepository: CoinrankingRepository,
    ) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchCoinViewModel::class.java)) {
                return SearchCoinViewModel(coinrankingRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}