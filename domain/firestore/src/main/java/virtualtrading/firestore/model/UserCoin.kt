package virtualtrading.firestore.model

data class UserCoins(
    val userUID: String,
    val coins: List<Coin>
)

data class Coin(
    val id: String,
    val amount: Double
)