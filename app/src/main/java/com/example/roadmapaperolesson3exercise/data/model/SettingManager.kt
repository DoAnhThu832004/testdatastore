package com.example.roadmapaperolesson3exercise.data.model

import android.content.Context
import androidx.compose.ui.res.booleanResource
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.roadmapaperolesson3exercise.extensions.catchIOException
import com.google.gson.annotations.Until
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("settings")
class SettingManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val DARK_MODE = booleanPreferencesKey("dark_mode")
        private val LANGUAGE = stringPreferencesKey("language")
        private val USERNAME = stringPreferencesKey("username")
        private val TICK = booleanPreferencesKey("tick")
        private val COUNT = intPreferencesKey("count")
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
        private val EXPIRATION_TIME = longPreferencesKey("expiration_time")
    }

    val darkModeFlow = context.dataStore.data
        .catchIOException()
        .map { it[DARK_MODE] ?: false }
    val languageFlow = context.dataStore.data
        .catchIOException()
        .map { it[LANGUAGE] ?: "en" }
    val usernameFlow = context.dataStore.data
        .catchIOException()
        .map { it[USERNAME] ?: "" }
    val tickFlow = context.dataStore.data
        .catchIOException()
        .map { it[TICK] ?: false }
    val countFlow = context.dataStore.data
        .catchIOException()
        .map { it[COUNT] ?: 0 }
    val accessTokenFlow = context.dataStore.data
        .catchIOException()
        .map { it[ACCESS_TOKEN] ?: "" }
    val expirationTimeFlow = context.dataStore.data
        .catchIOException()
        .map { it[EXPIRATION_TIME] ?: 0 }
    suspend fun setDarkMode(darkMode: Boolean) : Result<Unit> {
        return try {
            context.dataStore.edit {
                it[DARK_MODE] = darkMode
            }
            Result.success(Unit)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
    suspend fun setLanguage(language: String): Result<Unit> {
        return try {
            context.dataStore.edit {
                it[LANGUAGE] = language
            }
            Result.success(Unit)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
    suspend fun setUserName(username: String): Result<Unit> {
        return try {
            context.dataStore.edit {
                it[USERNAME] = username
            }
            Result.success(Unit)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
    suspend fun setTick(tick: Boolean): Result<Unit> {
        return try {
            context.dataStore.edit {
                it[TICK] = tick
            }
            Result.success(Unit)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
    suspend fun setCount(count: Int): Result<Unit> {
        return try {
            context.dataStore.edit {
                it[COUNT] = count
            }
            Result.success(Unit)
        } catch (e: IOException) {
            Result.failure(e)
        }
    }
    suspend fun saveSession(accessToken: String, expirationTime: Long): Result<Unit> {
        return try {
            context.dataStore.edit {
                it[ACCESS_TOKEN] = accessToken
                it[EXPIRATION_TIME] = expirationTime
            }
            Result.success(Unit)
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun cleanSession(): Result<Unit> {
        return try {
            context.dataStore.edit {
                it.clear()
            }
            Result.success(Unit)
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
// trong flow có các hàm sau:
//map	                đổi dữ liệu
//collect	            nhận dữ liệu
//catch	                bắt lỗi
//filter	            lọc
//debounce	            search
//distinctUntilChanged	tránh emit trùng
//combine	            gộp state
//flatMapLatest	        API search
//stateIn	            Compose state
//onEach	            log/loading

// trong datastore có các hàm sau:
// edit : mở transaction để sửa dữ liệu trong DataStore ,đọc giá trị hiện tại,thay đổi giá trị,tự động lưu xuống file
// map : đọc dữ liệu