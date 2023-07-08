package virtualtrading.coinranking


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchCoinDTO(
    @SerialName("data")
    val `data`: Data,
    @SerialName("status")
    val status: String
) {
    @Serializable
    data class Data(
        @SerialName("coins")
        val coins: List<Coin>,
        @SerialName("exchanges")
        val exchanges: List<Exchange>,
        @SerialName("markets")
        val markets: List<Market>
    ) {
        @Serializable
        data class Coin(
            @SerialName("iconUrl")
            val iconUrl: String,
            @SerialName("name")
            val name: String,
            @SerialName("price")
            val price: String?,
            @SerialName("symbol")
            val symbol: String,
            @SerialName("uuid")
            val uuid: String
        )

        @Serializable
        data class Exchange(
            @SerialName("iconUrl")
            val iconUrl: String,
            @SerialName("name")
            val name: String,
            @SerialName("recommended")
            val recommended: Boolean,
            @SerialName("uuid")
            val uuid: String
        )

        @Serializable
        data class Market(
            @SerialName("baseSymbol")
            val baseSymbol: String,
            @SerialName("baseUuid")
            val baseUuid: String,
            @SerialName("exchangeIconUrl")
            val exchangeIconUrl: String,
            @SerialName("exchangeName")
            val exchangeName: String,
            @SerialName("exchangeUuid")
            val exchangeUuid: String,
            @SerialName("quoteSymbol")
            val quoteSymbol: String,
            @SerialName("quoteUuid")
            val quoteUuid: String,
            @SerialName("recommended")
            val recommended: Boolean,
            @SerialName("uuid")
            val uuid: String
        )
    }
}

fun SearchCoinDTO.toCoinIds():List<String> {
    return this.data.coins.map { coin -> coin.uuid }
}