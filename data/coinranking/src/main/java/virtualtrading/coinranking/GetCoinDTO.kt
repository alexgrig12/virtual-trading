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
            val btcPrice: String = NO_INFO,
            @SerialName("change")
            val change: String = NO_INFO,
            @SerialName("coinrankingUrl")
            val coinrankingUrl: String,
            @SerialName("color")
            val color: String = NO_INFO,
            @SerialName("description")
            val description: String = NO_INFO,
            @SerialName("fullyDilutedMarketCap")
            val fullyDilutedMarketCap: String = NO_INFO,
            @SerialName("24hVolume")
            val hVolume: String = NO_INFO,
            @SerialName("iconUrl")
            val iconUrl: String,
            @SerialName("links")
            val links: List<Link?>?,
            @SerialName("listedAt")
            val listedAt: Int?,
            @SerialName("lowVolume")
            val lowVolume: Boolean?,
            @SerialName("marketCap")
            val marketCap: String = NO_INFO,
            @SerialName("name")
            val name: String = NO_INFO,
            @SerialName("notices")
            val notices: List<Notice>?,
            @SerialName("numberOfExchanges")
            val numberOfExchanges: Int = 0,
            @SerialName("numberOfMarkets")
            val numberOfMarkets: Int = 0,
            @SerialName("price")
            val price: String?,
            @SerialName("priceAt")
            val priceAt: Int?,
            @SerialName("rank")
            val rank: Int = -1,
            @SerialName("sparkline")
            val sparkline: List<String?>?,
            @SerialName("supply")
            val supply: Supply,
            @SerialName("symbol")
            val symbol: String = NO_INFO,
            @SerialName("tags")
            val tags: List<String>,
            @SerialName("uuid")
            val uuid: String,
            @SerialName("websiteUrl")
            val websiteUrl: String = NO_INFO,
        ) {
            @Serializable
            data class AllTimeHigh(
                @SerialName("price")
                val price: String = NO_INFO,
                @SerialName("timestamp")
                val timestamp: Int?,
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
            data class Notice(
                @SerialName("type")
                val type: String,
                @SerialName("value")
                val value: String,
            )

            @Serializable
            data class Supply(
                @SerialName("circulating")
                val circulating: String = NO_INFO,
                @SerialName("confirmed")
                val confirmed: Boolean?,
                @SerialName("max")
                val max: String = NO_INFO,
                @SerialName("supplyAt")
                val supplyAt: Int?,
                @SerialName("total")
                val total: String = NO_INFO,
            )
        }
    }
}

fun GetCoinDTO.toCoinDetails(): CoinDetails = CoinDetails(
    data.coin.uuid,
    data.coin.symbol,
    data.coin.name,
    data.coin.price.let { price ->
        if (price == null) GetCoinsDTO.NO_INFO
        else if (price.length <= 7) price
        else price.substring(0, 8)
    },
    data.coin.change,
    data.coin.rank,
    data.coin.marketCap,
    data.coin.supply.total,
    data.coin.supply.max,
    data.coin.supply.circulating,
    data.coin.description,
    isDecreased = data.coin.change.startsWith("-")
)