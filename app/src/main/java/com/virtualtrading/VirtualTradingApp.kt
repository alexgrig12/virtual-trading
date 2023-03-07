package com.virtualtrading

import android.app.Application
import com.virtualtrading.di.AppComponent
import com.virtualtrading.di.DaggerAppComponent
import virtualtrading.coins.api.CoinsDeps
import virtualtrading.coins.api.CoinsDepsProvider


class VirtualTradingApp : Application(), CoinsDepsProvider {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().coinRankingApiKey("some-api-key").build()
    }

    override fun onCreate() {
        super.onCreate()
    }

    override val deps: CoinsDeps = appComponent
}