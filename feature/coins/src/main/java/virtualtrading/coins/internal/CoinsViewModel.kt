package virtualtrading.coins.internal

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import virtualtrading.coinranking.CoinrankingRepository
import javax.inject.Inject


internal class CoinsViewModel(private val coinrankingRepository: CoinrankingRepository) : ViewModel() {

    val coins = flow {
        try {
            emit(coinrankingRepository.getCoins())
        } catch (e: java.lang.Exception) {
            Log.d(TAG, "Error", e)
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(private val coinrankingRepository: CoinrankingRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            require(modelClass == CoinsViewModel::class.java)
            return CoinsViewModel(coinrankingRepository) as T
        }
    }

    companion object {
        private const val TAG = "CoinsViewModel"
    }
}