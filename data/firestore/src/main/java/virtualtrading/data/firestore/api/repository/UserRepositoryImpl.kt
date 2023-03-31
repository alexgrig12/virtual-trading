package virtualtrading.data.firestore.api.repository

import com.google.firebase.firestore.SetOptions
import virtualtrading.data.firestore.internal.ColAndFields
import virtualtrading.data.firestore.internal.FirestoreService
import virtualtrading.firestore.model.User
import virtualtrading.firestore.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    private val service = FirestoreService()

    override fun addUser(userUID: String, user: User) {
        service.firestoreInstance.collection(ColAndFields.COL_USERS.name).document(userUID)
            .set(user)
    }

    override fun getUserBalance(userUID: String): Double {
        val userRef =
            service.firestoreInstance.collection(ColAndFields.COL_USERS.name).document(userUID)
        val task = userRef.get()
        return if (task.isComplete) {
            task.result.getDouble(ColAndFields.FIELD_BALANCE.name)!!
        } else
            0.0
    }

    override fun changeUserBalance(userUID: String, newBalance: Double) {
        val userRef =
            service.firestoreInstance.collection(ColAndFields.COL_USERS.name).document(userUID)
        val data = hashMapOf(ColAndFields.FIELD_BALANCE.name to newBalance)
        userRef.set(data, SetOptions.merge())
    }

    override fun changeUserEmail(userUID: String, newEmail: String) {
        TODO("Not yet implemented")
    }

    override fun changeUserIcon(userUID: String, newIconUri: String) {
        TODO("Not yet implemented")
    }

}