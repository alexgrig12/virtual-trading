package virtualtrading.firestore.repository

import virtualtrading.firestore.model.User

interface UserRepository {
    fun addUser(userUID: String, user: User)
    fun getUserBalance(userUID: String): Double
    fun changeUserBalance(userUID: String, newBalance: Double)
    fun changeUserEmail(userUID: String, newEmail: String)
    fun changeUserIcon(userUID: String, newIconUri: String)
}