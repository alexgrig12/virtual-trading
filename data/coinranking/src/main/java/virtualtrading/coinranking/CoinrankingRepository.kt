package virtualtrading.coinranking

class CoinrankingRepository constructor(private val coinrankingService: CoinrankingService) {
    suspend fun getCoins(
        orderBy: String, amount: Int = 50, ids: List<String>? = null,
    ) = coinrankingService.getCoins(orderBy = orderBy, amount = amount, ids = ids).toCoins()

    suspend fun getFavoriteRecomendations(): List<RecommendedCoin> = coinrankingService.getCoins(amount = 6).toRecommended()

    // Return type need to be change by mapping
    suspend fun getCoinById(id: String): GetCoinDTO = coinrankingService.getCoinById(id)

    suspend fun getCoinDetailsById(id: String): CoinDetails = coinrankingService.getCoinById(id).toCoinDetails()
}