package com.toren.news.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreRepository
    @Inject constructor(
        private val dataStore: DataStore<Preferences>
    ) {

    companion object {
        val SEARCH_HISTORY_KEY = stringSetPreferencesKey("HISTORY")
    }

    suspend fun saveQuery(text: String) {
        dataStore.edit {
            val currentList = it[SEARCH_HISTORY_KEY] ?: emptySet()
            val updatedList = currentList.toMutableSet()
            updatedList.add(text)
            it[SEARCH_HISTORY_KEY] = updatedList.toSet()
        }
    }

    fun getQueryHistory(): Flow<Set<String>> {
        return dataStore.data
            .map { preferences ->
                preferences[SEARCH_HISTORY_KEY] ?: emptySet()
            }
    }
}