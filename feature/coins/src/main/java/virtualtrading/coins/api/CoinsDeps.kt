package virtualtrading.coins.api

import virtualtrading.coinranking.CoinrankingService

interface CoinsDeps {
    val coinRankingService: CoinrankingService
}

interface CoinsDepsProvider {
    val deps:CoinsDeps
}