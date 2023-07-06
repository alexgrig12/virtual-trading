package virtualtrading.coindetails.internal.di

import android.app.Application
import android.content.Context
import dagger.Component
import virtualtrading.coindetails.api.CoinDetailsDeps
import virtualtrading.coindetails.api.CoinDetailsDepsProvider
import virtualtrading.coindetails.api.CoinDetailsFragment
import javax.inject.Scope

@Scope
internal annotation class CoinDetailsScope

@[CoinDetailsScope Component(
    dependencies = [CoinDetailsDeps::class],
)]
internal interface CoinDetailsComponent {
    fun inject(fragment: CoinDetailsFragment)

    @Component.Builder
    interface Builder {
        fun deps(coinsDeps: CoinDetailsDeps): Builder
        fun build(): CoinDetailsComponent
    }
}

val Context.coinDetailsDepsProvider: CoinDetailsDepsProvider
    get() = when (this) {
        is CoinDetailsDepsProvider -> this
        is Application -> error("Application must implement CoinDetailsDepsProvider")
        else -> applicationContext.coinDetailsDepsProvider
    }