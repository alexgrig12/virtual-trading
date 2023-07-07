package virtualtrading.searchcoin.internal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import virtualtrading.coinranking.CoinrankingRepository
import javax.inject.Inject

internal class SearchCoinViewModel(private val coinrankingRepository: CoinrankingRepository) : ViewModel() {



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