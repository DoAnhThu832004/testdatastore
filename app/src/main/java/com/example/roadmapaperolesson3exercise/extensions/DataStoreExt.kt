package com.example.roadmapaperolesson3exercise.extensions

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException

fun Flow<Preferences>.catchIOException(): Flow<Preferences> {
    return this
        .retryWhen { cause, attempt -> // retry là số lần thử lại, cause exception xảy ra
            if(cause is IOException && attempt <  3) {
                delay(500L)
                true
            } else {
                false
            }
        }
        .catch {
            if(it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
}