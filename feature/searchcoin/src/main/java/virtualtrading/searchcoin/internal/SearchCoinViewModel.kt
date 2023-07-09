package virtualtrading.searchcoin.internal

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import virtualtrading.coinranking.Coin
import virtualtrading.coinranking.CoinrankingRepository
import javax.inject.Inject

internal class SearchCoinViewModel(private val coinrankingRepository: CoinrankingRepository) : ViewModel() {

    private val _query = MutableStateFlow("")
    var query: String
        get() = _query.value
        set(value) {
            _query.value = value
        }

    // b , bi, bit , ... , bitcoin , bitcoin , BiTcOin --> bitcoin

    val searchResult: LiveData<List<Coin>> = _query
        .debounce(500)  // b, bi, bit,..., bitcoin --> bitcoin
        .filter { currentQuery ->
            return@filter currentQuery.isNotEmpty() // clearing search input will not trigger request
        }
        .map { currentQuery -> currentQuery.lowercase().trim() } // BiTcOin --> bitcoin
        .distinctUntilChanged() // bitcoin, bitcoin, bitcoin, bitcoin --> bitcoin
        .flatMapLatest { currentQuery ->
            searchCoin(currentQuery) // request
        }
        .asLiveData()


    private suspend fun searchCoin(query: String): Flow<List<Coin>> {
        return flow {
            val coinIds: List<String> = coinrankingRepository.getCoinsIdsBySearch(query)
            Log.d(TAG, "searchCoin:coinId: $coinIds ")
            val coins = coinrankingRepository.getCoins(ids = coinIds)
            Log.d(TAG, "searchCoin:coins: $coins")
            emit(coins)
        }.flowOn(Dispatchers.IO)
    }

    companion object {
        private const val TAG = "SearchCoinViewModel"
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