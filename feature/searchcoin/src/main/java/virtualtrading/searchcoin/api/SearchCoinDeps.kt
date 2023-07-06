package virtualtrading.searchcoin.api

import virtualtrading.coinranking.CoinrankingRepository

interface SearchCoinDeps {
    val coinrankingRepository: CoinrankingRepository
}

interface SearchCoinDepsProvider {
    val searchCoinDeps: SearchCoinDeps
}