package virtualtrading.coinranking

class CoinrankingRepository constructor(private val coinrankingService: CoinrankingService) {
    suspend fun getCoins(orderBy: String) = coinrankingService.getCoins(orderBy = orderBy).toCoins()

    suspend fun getFavoriteRecomendations(): List<RecommendedCoin> = coinrankingService.getCoins(amount = 6).toRecommended()
}