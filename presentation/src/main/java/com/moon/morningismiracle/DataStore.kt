package com.moon.morningismiracle

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStore @Inject constructor(@ApplicationContext private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = DATA_STORE_NAME)

    private val lastVisitDateKey = longPreferencesKey(LAST_VISIT_KEY) // String 타입 저장 키값

    // 새로 방문한 날짜를 업데이트 한다
    val lastDate: Flow<Long> =
        context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[lastVisitDateKey] ?: 0L  // 아까 만든 Key 이용
            }

    // 새로 방문한 날짜를 업데이트 한다
    suspend fun setLastDate(newDate: Long) {
        context.dataStore.edit { preferences ->
            preferences[lastVisitDateKey] = newDate  // 아까 만든 Key 이용
        }
    }

    companion object {
        const val DATA_STORE_NAME = "visitInfo"
        const val LAST_VISIT_KEY = "last_visit_date"
    }
}