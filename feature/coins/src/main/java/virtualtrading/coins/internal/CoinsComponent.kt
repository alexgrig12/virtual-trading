package virtualtrading.coins.internal

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import virtualtrading.coins.api.CoinsDeps
import virtualtrading.coins.api.CoinsDepsProvider
import virtualtrading.coins.api.CoinsFragment
import javax.inject.Scope

@Scope
annotation class CoinsScope

@[CoinsScope Component(
    dependencies = [CoinsDeps::class],
    modules = [CoinsModule::class]
)]
internal interface CoinsComponent {
    fun inject(fragment: CoinsFragment)

    @Component.Builder
    interface Builder {
        fun deps(coinsDeps: CoinsDeps): Builder
        fun build(): CoinsComponent
    }
}

@Module
internal class CoinsModule

val Context.coinsDepsProvider: CoinsDepsProvider
    get() = when (this) {
        is CoinsDepsProvider -> this
        is Application -> error("Application must implement CoinsDepsProvider")
        else -> applicationContext.coinsDepsProvider
    }