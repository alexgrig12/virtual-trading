package virtualtrading.coindetails.internal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import virtualtrading.coinranking.CoinrankingRepository
import javax.inject.Inject

class CoinDetailsViewModel(coinrankingRepository: CoinrankingRepository) : ViewModel() {

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(private val coinrankingRepository: CoinrankingRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == CoinDetailsViewModel::class.java)
            return CoinDetailsViewModel(coinrankingRepository) as T
        }
    }

    companion object {
        private const val TAG = "CoinDetailsViewModel"
    }

}