package com.virtualtrading

import android.app.Application
import com.virtualtrading.di.AppComponent
import com.virtualtrading.di.DaggerAppComponent
import virtualtrading.coins.api.CoinsDeps
import virtualtrading.coins.api.CoinsDepsProvider
import virtualtrading.data.firestore.api.datastore.UserPreferences


class VirtualTradingApp : Application(), CoinsDepsProvider {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().build()
    }

    override val deps: CoinsDeps = appComponent
}