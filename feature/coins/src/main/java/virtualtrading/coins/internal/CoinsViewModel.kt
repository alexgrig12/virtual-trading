package virtualtrading.coins.internal

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import virtualtrading.coinranking.CoinrankingService
import javax.inject.Inject


internal class CoinsViewModel(private val coinrankingService: CoinrankingService) : ViewModel() {


    fun logRanking () {
        Log.d("CoinsViewModel", "apiKey in ViewModel: ${coinrankingService.apiKey}")
    }

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(private val coinrankingService: CoinrankingService) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == CoinsViewModel::class.java)
            return CoinsViewModel(coinrankingService) as T
        }
    }
}