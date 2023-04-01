package virtualtrading.firestore.api.repository

import virtualtrading.firestore.internal.FirestoreService
import virtualtrading.firestore.repository.UserCoinRepository

class UserCoinRepositoryImpl : UserCoinRepository {
    private val service = FirestoreService()

}