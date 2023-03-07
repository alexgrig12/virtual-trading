package virtualtrading.coins.internal

import android.app.Application
import androidx.lifecycle.AndroidViewModel

internal class CoinsComponentViewModel(private val application: Application) : AndroidViewModel(application) {
    val coinsComponent: CoinsComponent by lazy {
        DaggerCoinsComponent.builder().deps(application.coinsDepsProvider.deps).build()
    }
}