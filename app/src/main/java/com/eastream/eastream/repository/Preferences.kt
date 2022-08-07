package com.eastream.eastream.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences

public suspend fun DataStore<Preferences>.edit(
    transform: suspend (MutablePreferences) -> Unit
): Preferences {
    return this.updateData {
        it.toMutablePreferences().apply { transform(this)}
    }
}