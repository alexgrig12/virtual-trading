package com.virtualtrading.di

import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import virtualtrading.coinranking.CoinrankingService
import virtualtrading.coins.api.CoinsDeps
import javax.inject.Qualifier
import javax.inject.Scope


@[AppScope Component(modules = [AppModule::class])]
interface AppComponent : CoinsDeps {
    override val coinRankingService: CoinrankingService

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun coinRankingApiKey(@CoinrankingApiQualifier apiKey: String): Builder
        fun build(): AppComponent
    }
}


@Module
class AppModule {

    @[Provides AppScope]
    fun provideCoinrankingService(@CoinrankingApiQualifier apiKey: String): CoinrankingService = CoinrankingService(apiKey)

}

@Scope
annotation class AppScope

@Qualifier
annotation class CoinrankingApiQualifier