package com.example.roadmapaperolesson3exercise.core.common

sealed interface UiEvent {
    data class ShowSnackbar(val message: String): UiEvent
}