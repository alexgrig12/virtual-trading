package com.virtualtrading

import android.app.Application
import com.virtualtrading.di.AppComponent
import com.virtualtrading.di.DaggerAppComponent
import virtualtrading.coindetails.api.CoinDetailsDeps
import virtualtrading.coindetails.api.CoinDetailsDepsProvider
import virtualtrading.coins.api.CoinsDeps
import virtualtrading.coins.api.CoinsDepsProvider


class VirtualTradingApp : Application(), CoinsDepsProvider, CoinDetailsDepsProvider {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

    override fun onCreate() {
        super.onCreate()
    }

    override val deps: CoinsDeps = appComponent
    override val coinDetailsDeps: CoinDetailsDeps = appComponent
}