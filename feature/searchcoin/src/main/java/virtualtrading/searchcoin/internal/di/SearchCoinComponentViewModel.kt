package virtualtrading.searchcoin.internal.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel

internal class SearchCoinComponentViewModel(private val application: Application) : AndroidViewModel(application) {
    val searchCoinComponent: SearchCoinComponent by lazy {
        DaggerSearchCoinComponent.builder().deps(application.searchCoinDepsProvider.searchCoinDeps).build()
    }
}