package virtualtrading.coinactions

data class Coin(
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