package virtualtrading.data.firestore.api.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import virtualtrading.firestore.model.User
import java.io.IOException


class UserPreferences private constructor(
    context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_data_store")
    private val dataStore = context.dataStore

    companion object {
        private var instance: UserPreferences? = null

        fun getInstance(context: Context): UserPreferences {
            return instance ?: synchronized(this) {
                instance ?: UserPreferences(context).also { instance = it }
            }
        }

        private val KEY_UID = stringPreferencesKey("key_uid")
        private val KEY_EMAIL = stringPreferencesKey("key_email")
        private val KEY_NAME = stringPreferencesKey("key_name")
        private val KEY_ICON_URI = stringPreferencesKey("key_icon_uri")
        private val KEY_BALANCE = doublePreferencesKey("key_balance")
    }

    suspend fun saveUser(userUID: String, user: User) {
        dataStore.edit {
            it[KEY_EMAIL] = user.email
            it[KEY_NAME] = user.name
            it[KEY_ICON_URI] = user.iconUri
            it[KEY_BALANCE] = user.balance
            saveUserUID(userUID)
        }
    }

    private suspend fun saveUserUID(userUID: String) {
        dataStore.edit {
            it[KEY_UID] = userUID
        }
    }

    fun getUser(): Flow<User?> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { pref ->
            val user =  User(
                pref[KEY_EMAIL]!!,
                pref[KEY_NAME]!!,
                pref[KEY_ICON_URI]!!,
                pref[KEY_BALANCE]!!
            ) ?: null
                user
        }
    }

    fun getUserUID(): Flow<String> {
        return dataStore.data.map {
            it[KEY_UID]!!
        }
    }
}