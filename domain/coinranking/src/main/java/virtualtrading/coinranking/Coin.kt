package virtualtrading.coinranking

data class Coin(
    val uuid: String,
    val change: String,
    val iconUrl: String,
    val name: String,
    val price: String,
    val symbol: String,
    val urlFormat: CoinsImageFormat,
    val isDecreased: Boolean,
)

enum class CoinsImageFormat(val format: String) {
    SVG("svg"),
    PNG("png")
}