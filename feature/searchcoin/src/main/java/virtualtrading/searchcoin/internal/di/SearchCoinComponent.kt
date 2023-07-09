package virtualtrading.searchcoin.internal.di

import android.app.Application
import android.content.Context
import dagger.Component
import dagger.Module
import virtualtrading.searchcoin.api.SearchCoinDeps
import virtualtrading.searchcoin.api.SearchCoinDepsProvider
import virtualtrading.searchcoin.api.SearchCoinFragment
import javax.inject.Scope

@Scope
annotation class SearchCoinScope

@[SearchCoinScope Component(
    dependencies = [SearchCoinDeps::class],
    modules = [SearchCoinModule::class]
)]
internal interface SearchCoinComponent {

    fun inject(searchCoinFragment: SearchCoinFragment)

    @Component.Builder
    interface Builder {
        fun deps(coinsDeps: SearchCoinDeps): Builder
        fun build(): SearchCoinComponent
    }
}

@Module
internal class SearchCoinModule

val Context.searchCoinDepsProvider: SearchCoinDepsProvider
    get() = when (this) {
        is SearchCoinDepsProvider -> this
        is Application -> error("Application must implement CoinsDepsProvider")
        else -> applicationContext.searchCoinDepsProvider
    }