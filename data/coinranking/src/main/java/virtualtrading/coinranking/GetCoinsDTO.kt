package virtualtrading.coinranking


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCoinsDTO(
    @SerialName("data")
    val `data`: DataDTO,
    @SerialName("status")
    val status: String,
) {
    @Serializable
    data class DataDTO(
        @SerialName("coins")
        val coins: List<CoinDTO>,
        @SerialName("stats")
        val stats: StatsDTO,
    ) {
        @Serializable
        data class CoinDTO(
            @SerialName("btcPrice")
            val btcPrice: String?,
            @SerialName("change")
            val change: String,
            @SerialName("coinrankingUrl")
            val coinrankingUrl: String?,
            @SerialName("color")
            val color: String?,
            @SerialName("24hVolume")
            val hVolume: String?,
            @SerialName("iconUrl")
            val iconUrl: String,
            @SerialName("listedAt")
            val listedAt: Int?,
            @SerialName("marketCap")
            val marketCap: String,
            @SerialName("name")
            val name: String,
            @SerialName("price")
            val price: String,
            @SerialName("rank")
            val rank: Int?,
            @SerialName("sparkline")
            val sparkline: List<String?>?,
            @SerialName("symbol")
            val symbol: String,
            @SerialName("uuid")
            val uuid: String,
        )

        @Serializable
        data class StatsDTO(
            @SerialName("total")
            val total: Int,
            @SerialName("total24hVolume")
            val total24hVolume: String,
            @SerialName("totalCoins")
            val totalCoins: Int,
            @SerialName("totalExchanges")
            val totalExchanges: Int,
            @SerialName("totalMarketCap")
            val totalMarketCap: String,
            @SerialName("totalMarkets")
            val totalMarkets: Int,
        )
    }
}

fun GetCoinsDTO.toCoins(): List<Coin> {
    return this.data.coins.map { it.toCoin() }

}

fun GetCoinsDTO.DataDTO.CoinDTO.toCoin(): Coin = Coin(
    uuid = this.uuid,
    change = this.change,
    iconUrl = this.iconUrl,
    name = this.name,
    price = this.price.substring(0, 8),
    symbol = this.symbol,
    urlFormat = this.iconUrl.let { str ->
        str.lastIndexOf('.', str.length, false).let { index ->
            str.substring(index + 1, index + 4).let { format ->
                if (format == CoinsImageFormat.SVG.format) CoinsImageFormat.SVG else CoinsImageFormat.PNG
            }
        }
    },
    isDecreased = this.change.startsWith("-")
)


fun GetCoinsDTO.toRecommended(): List<RecommendedCoin> {
    return this.data.coins.map { it.toRecommendedCoin() }
}

fun GetCoinsDTO.DataDTO.CoinDTO.toRecommendedCoin(): RecommendedCoin = RecommendedCoin(
    uuid = this.uuid,
    change = this.change,
    name = this.name,
    price = this.price.substring(0, 8),
    symbol = this.symbol,
    isDecreased = this.change.startsWith("-"),
    isChoosed = false
)
