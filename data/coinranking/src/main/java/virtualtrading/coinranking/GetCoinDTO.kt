package virtualtrading.coinranking


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCoinDTO(
    @SerialName("data")
    val `data`: Data,
    @SerialName("status")
    val status: String,
) {
    companion object {
        const val NO_INFO = "No info"
    }

    @Serializable
    data class Data(
        @SerialName("coin")
        val coin: Coin,
    ) {
        @Serializable
        data class Coin(
            @SerialName("allTimeHigh")
            val allTimeHigh: AllTimeHigh,
            @SerialName("btcPrice")
            val btcPrice: String,
            @SerialName("change")
            val change: String,
            @SerialName("coinrankingUrl")
            val coinrankingUrl: String,
            @SerialName("color")
            val color: String,
            @SerialName("description")
            val description: String,
            @SerialName("fullyDilutedMarketCap")
            val fullyDilutedMarketCap: String,
            @SerialName("24hVolume")
            val hVolume: String,
            @SerialName("iconUrl")
            val iconUrl: String,
            @SerialName("links")
            val links: List<Link>,
            @SerialName("listedAt")
            val listedAt: Int,
            @SerialName("lowVolume")
            val lowVolume: Boolean,
            @SerialName("marketCap")
            val marketCap: String,
            @SerialName("name")
            val name: String,
            @SerialName("notices")
            val notices: Notices?,
            @SerialName("numberOfExchanges")
            val numberOfExchanges: Int,
            @SerialName("numberOfMarkets")
            val numberOfMarkets: Int,
            @SerialName("price")
            val price: String,
            @SerialName("priceAt")
            val priceAt: Int,
            @SerialName("rank")
            val rank: Int,
            @SerialName("sparkline")
            val sparkline: List<String>,
            @SerialName("supply")
            val supply: Supply,
            @SerialName("symbol")
            val symbol: String,
            @SerialName("tags")
            val tags: List<String>,
            @SerialName("uuid")
            val uuid: String,
            @SerialName("websiteUrl")
            val websiteUrl: String,
        ) {
            @Serializable
            data class AllTimeHigh(
                @SerialName("price")
                val price: String,
                @SerialName("timestamp")
                val timestamp: Int,
            )

            @Serializable
            data class Link(
                @SerialName("name")
                val name: String,
                @SerialName("type")
                val type: String,
                @SerialName("url")
                val url: String,
            )

            @Serializable
            data class Notices(
                val notices: List<Notice>,
            )

            @Serializable
            data class Notice(
                @SerialName("type")
                val type: String,
                @SerialName("value")
                val value: String,
            )

            @Serializable
            data class Supply(
                @SerialName("circulating")
                val circulating: String?,
                @SerialName("confirmed")
                val confirmed: Boolean,
                @SerialName("max")
                val max: String?,
                @SerialName("supplyAt")
                val supplyAt: Int,
                @SerialName("total")
                val total: String?,
            )
        }
    }


}

fun GetCoinDTO.toCoinDetails(): CoinDetails = CoinDetails(
    this.data.coin.uuid,
    this.data.coin.symbol,
    this.data.coin.name,
    this.data.coin.price.substring(0, 8),
    this.data.coin.change,
    this.data.coin.rank,
    this.data.coin.marketCap,
    this.data.coin.supply.total ?: GetCoinDTO.NO_INFO,
    this.data.coin.supply.max ?: GetCoinDTO.NO_INFO,
    this.data.coin.supply.circulating ?: GetCoinDTO.NO_INFO,
    this.data.coin.description,
    isDecreased = this.data.coin.change.startsWith("-")
)