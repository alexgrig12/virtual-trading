package virtualtrading.firestore.internal

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

internal class FirestoreService {
    val firestoreInstance: FirebaseFirestore = Firebase.firestore
}

internal enum class ColAndFields(name: String) {
    COL_USERS("users"),
    COL_USER_COINS("user_coins"),

    FIELD_ID("id"),
    FIELD_AMOUNT("amount"),
    FIELD_SUM("sum"),

    FIELD_UID("uid"),
    FIELD_EMAIL("email"),
    FIELD_NAME("name"),
    FIELD_ICON_URI("icon_uri"),
    FIELD_BALANCE("balance"),
}