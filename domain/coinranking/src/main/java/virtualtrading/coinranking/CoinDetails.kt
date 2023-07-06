package virtualtrading.coinranking

data class CoinDetails(
    val uuid: String,
    val symbol: String,
    val name: String,
    val price: String,
    val change: String,
    val rank: Int,
    val marketCap: String,
    val totalSupply: String,
    val maxSupply: String,
    val circulatingSupply: String,
    val description: String,
    val isDecreased: Boolean,
)