package virtualtrading.coinranking

class CoinrankingRepository constructor(private val coinrankingService: CoinrankingService) {
    suspend fun getCoins(orderBy: String) = coinrankingService.getCoins(orderBy = orderBy).toCoins()
}