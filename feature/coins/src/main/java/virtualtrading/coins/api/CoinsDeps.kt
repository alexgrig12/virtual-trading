package virtualtrading.coins.api

import virtualtrading.coinranking.CoinrankingRepository

interface CoinsDeps {
    val coinrankingRepository: CoinrankingRepository
}

interface CoinsDepsProvider {
    val deps: CoinsDeps
}