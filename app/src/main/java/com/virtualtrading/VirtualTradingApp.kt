package com.virtualtrading

import android.app.Application
import com.virtualtrading.di.AppComponent
import com.virtualtrading.di.DaggerAppComponent
import virtualtrading.coindetails.api.CoinDetailsDeps
import virtualtrading.coindetails.api.CoinDetailsDepsProvider
import virtualtrading.coins.api.CoinsDeps
import virtualtrading.coins.api.CoinsDepsProvider
import virtualtrading.searchcoin.api.SearchCoinDeps
import virtualtrading.searchcoin.api.SearchCoinDepsProvider


class VirtualTradingApp : Application(), CoinsDepsProvider, CoinDetailsDepsProvider, SearchCoinDepsProvider {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

    override fun onCreate() {
        super.onCreate()
    }

    override val deps: CoinsDeps = appComponent
    override val coinDetailsDeps: CoinDetailsDeps = appComponent
    override val searchCoinDeps: SearchCoinDeps = appComponent

}