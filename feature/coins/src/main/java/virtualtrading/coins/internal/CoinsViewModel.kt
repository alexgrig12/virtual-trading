package virtualtrading.coins.internal

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import virtualtrading.coinranking.CoinrankingRepository
import virtualtrading.coinranking.CoinsOrderBy
import virtualtrading.coinactions.Coin
import javax.inject.Inject


internal class CoinsViewModel(private val coinrankingRepository: CoinrankingRepository) : ViewModel() {

    private val _coins = MutableLiveData<List<Coin>>()
    val coins: LiveData<List<Coin>>
        get() = _coins

    init {
        getCoins(CoinsOrderBy.MARKETCAP)
    }

    fun getCoins(orderBy: CoinsOrderBy) {
        viewModelScope.launch {
            coinrankingRepository.getCoins(orderBy.stringValue).let { coinList ->
                _coins.value = coinList
            }
        }
    }


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