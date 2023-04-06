package com.virtualtrading.di

import dagger.Component
import dagger.Module
import dagger.Provides
import virtualtrading.coindetails.api.CoinDetailsDeps
import virtualtrading.coinranking.CoinrankingRepository
import virtualtrading.coinranking.CoinrankingService
import virtualtrading.coins.api.CoinsDeps
import javax.inject.Qualifier
import javax.inject.Scope


@[AppScope Component(modules = [AppModule::class])]
interface AppComponent : CoinsDeps, CoinDetailsDeps {
    override val coinrankingRepository: CoinrankingRepository

    @Component.Builder
    interface Builder {

        fun build(): AppComponent
    }
}


@Module
class AppModule {

    @[Provides AppScope]
    fun provideCoinrankingService(): CoinrankingService = CoinrankingService()

    @[Provides AppScope]
    fun provideCoinrankingRepository(coinrankingService: CoinrankingService): CoinrankingRepository = CoinrankingRepository(coinrankingService)

}

@Scope
annotation class AppScope

@Qualifier
annotation class CoinrankingApiQualifier