package virtualtrading.coinranking

data class RecommendedCoin(
    val uuid: String,
    val change: String,
    val name: String,
    val price: String,
    val symbol: String,
    val isDecreased: Boolean,
    val isChoosed: Boolean,
)