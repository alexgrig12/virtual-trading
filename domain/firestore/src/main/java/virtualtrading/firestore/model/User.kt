package virtualtrading.firestore.model

data class User(
    val email: String,
    val name: String,
    val iconUri: String,
    val balance: Double
)