package virtualtrading.coindetails.api

import virtualtrading.coinranking.CoinrankingRepository

interface CoinDetailsDeps {
    val coinrankingRepository: CoinrankingRepository
}

interface CoinDetailsDepsProvider {
    val coinDetailsDeps: CoinDetailsDeps
}