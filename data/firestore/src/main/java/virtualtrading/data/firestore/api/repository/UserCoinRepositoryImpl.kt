package virtualtrading.data.firestore.api.repository

import virtualtrading.data.firestore.internal.FirestoreService
import virtualtrading.firestore.repository.UserCoinRepository

class UserCoinRepositoryImpl : UserCoinRepository {
    private val service = FirestoreService()

}