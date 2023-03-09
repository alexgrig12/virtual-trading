package virtualtrading.coinranking

class CoinrankingRepository constructor(private val coinrankingService: CoinrankingService) {
    suspend fun getCoins() = coinrankingService.getCoins(3).toCoins()
}