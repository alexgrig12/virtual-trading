package virtualtrading.coins.internal

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import virtualtrading.coinranking.Coin
import virtualtrading.coinranking.CoinrankingRepository
import virtualtrading.coinranking.CoinsOrderBy
import virtualtrading.coinranking.RecommendedCoin
import javax.inject.Inject


internal class CoinsViewModel(private val coinrankingRepository: CoinrankingRepository) : ViewModel() {

    private val _coins = MutableLiveData<List<Coin>>()
    val coins: LiveData<List<Coin>>
        get() = _coins

    private val _recommendedToBeFavorite = MutableLiveData<List<RecommendedCoin>>()
    val recommendedToBeFavorite: LiveData<List<RecommendedCoin>>
        get() = _recommendedToBeFavorite

    init {
        getCoins(CoinsOrderBy.MARKETCAP)
        getRecommendedFavorites()
    }

    fun getCoins(orderBy: CoinsOrderBy) {
        viewModelScope.launch {
            coinrankingRepository.getCoins(orderBy.stringValue).let { coinList ->
                _coins.value = coinList
            }
        }
    }

    private fun getRecommendedFavorites() {
        viewModelScope.launch {
            coinrankingRepository.getFavoriteRecomendations().let { recommendedCoins ->
                _recommendedToBeFavorite.value = recommendedCoins
            }
        }
    }

    fun toggleRecommended(recommendedCoin: RecommendedCoin) {
        val recommendedCoins: MutableList<RecommendedCoin> = _recommendedToBeFavorite.value?.toMutableList() ?: mutableListOf()
        val coinIndex = recommendedCoins.indexOf(recommendedCoin)
        val newCoin = recommendedCoin.copy(isChoosed = recommendedCoin.isChoosed.not())
        recommendedCoins.apply {
            removeAt(coinIndex)
            add(coinIndex, newCoin)
        }
        _recommendedToBeFavorite.value = recommendedCoins.toList()
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