package virtualtrading.coindetails.internal.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel

internal class CoinDetailsComponentViewModel(private val application: Application) : AndroidViewModel(application) {
    val coinDetailsComponent: CoinDetailsComponent by lazy {
        DaggerCoinDetailsComponent.builder().deps(application.coinDetailsDepsProvider.coinDetailsDeps).build()
    }
}