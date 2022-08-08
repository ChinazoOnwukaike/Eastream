package com.eastream.eastream.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import okio.IOException


class StoreThemeMode(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(("settings"))
        val THEME_KEY = booleanPreferencesKey("theme")
        val TOGGLE_KEY = booleanPreferencesKey("switch")
    }

    //get saved bool
    val getTheme: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[THEME_KEY] ?: true
        }

    //save switch to datastore
    suspend fun toggleTheme(isNightMode: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = isNightMode
        }
    }

    //get saved bool
    val getSwitch: Flow<Boolean?> = context.dataStore.data
        .map { preferences ->
            preferences[TOGGLE_KEY] ?: true
        }

    //save switch to datastore
    suspend fun toggleSwitch(checkedState: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[TOGGLE_KEY] = checkedState
        }
    }


}


//data class UserPreferences(
//    val isNightMode: Boolean
//)
//
//class UserPreferencesRepository(private val dataStore: DataStore<Preferences>) {
//    private val TAG: String = "UserPreferencesRepo"
//
//    private object PreferencesKeys {
//        val IS_NIGHT_MODE = booleanPreferencesKey("isNightMode")
//    }
//
//    val userReferencesFlow: Flow<UserPreferences> = dataStore.data
//        .catch { ex ->
//            if (ex is IOException) {
//                Log.e(TAG, "Error reading preferences", ex)
//                emit(emptyPreferences())
//            } else {
//                throw ex
//            }
//        }.map { preferences ->
//            mapPreferences(preferences)
//        }
//
//    suspend fun updateIsNightMode(isNightMode: Boolean) {
//        dataStore.edit { preferences ->
//            preferences[PreferencesKeys.IS_NIGHT_MODE] = isNightMode
//        }
//    }
//
//    suspend fun fetchInitialPreferences() = mapPreferences(dataStore.data.first().toPreferences())
//
//    private fun mapPreferences(preferences: Preferences): UserPreferences {
//        val isNightMode = preferences[PreferencesKeys.IS_NIGHT_MODE]?: true
//        return UserPreferences(isNightMode)
//    }
//}

