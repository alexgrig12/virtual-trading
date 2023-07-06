package virtualtrading.coindetails.internal

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import virtualtrading.coinranking.CoinDetails
import virtualtrading.coinranking.CoinrankingRepository
import javax.inject.Inject

class CoinDetailsViewModel(private val coinrankingRepository: CoinrankingRepository) : ViewModel() {

    private val _currentCoin = MutableLiveData<CoinDetails>()
    val currentCoin: LiveData<CoinDetails>
        get() = _currentCoin

    fun getCoinById(coidId: String) {
        viewModelScope.launch {
            coinrankingRepository.getCoinDetailsById(coidId).let { newCoin ->
                _currentCoin.value = newCoin
            }
        }
    }

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