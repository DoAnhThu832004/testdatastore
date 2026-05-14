package com.example.roadmapaperolesson3exercise.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roadmapaperolesson3exercise.core.common.Resource
import com.example.roadmapaperolesson3exercise.core.common.UiEvent
import com.example.roadmapaperolesson3exercise.data.model.SettingManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val settingManager: SettingManager
): ViewModel() {
    val dataStoreState : StateFlow<Resource<DataStoreStateUI>> = combine(
        settingManager.darkModeFlow,
        settingManager.languageFlow,
        settingManager.usernameFlow,
        settingManager.tickFlow,
        settingManager.countFlow
    ) { isDark, lang, username,tick,count ->
        // Bọc dữ liệu (ruột) vào trong Resource.Success (vỏ)
        Resource.Success(
            DataStoreStateUI(
                darkMode = isDark,
                language = lang,
                username = username,
                tick = tick,
                count = count
            )
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        // Trạng thái mặc định ban đầu phải là Loading của Resource
        initialValue = Resource.Loading
    )
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    fun setDarkMode(isDark: Boolean) {
        viewModelScope.launch {
            settingManager.setDarkMode(isDark).onFailure { error ->
                _uiEvent.send(
                    UiEvent.ShowSnackbar("Khong the luu giao dien: ${error.message}")
                )
            }
        }
    }
    fun setLanguage(language: String) {
        viewModelScope.launch {
            settingManager.setLanguage(language).onFailure { error ->
                _uiEvent.send(
                    UiEvent.ShowSnackbar("Khong the luu ngon ngu: ${error.message}")
                )
            }
        }
    }
    fun setUserName(username: String) {
        viewModelScope.launch {
            settingManager.setUserName(username).onFailure { error ->
                _uiEvent.send(
                    UiEvent.ShowSnackbar("Khong the luu ten nguoi dung: ${error.message}")
                )
            }
        }
    }
    fun setTick(tick: Boolean) {
        viewModelScope.launch {
            settingManager.setTick(tick).onFailure { error ->
                _uiEvent.send(
                    UiEvent.ShowSnackbar("Khong the luu tick: ${error.message}")
                )
            }
        }
    }
    fun setIncrementCount(count: Int) {
        viewModelScope.launch {
            settingManager.setCount(count + 1).onFailure { error ->
                _uiEvent.send(
                    UiEvent.ShowSnackbar("Khong the tang count: ${error.message}")
                )
            }
        }
    }
    fun setDecrementCount(count: Int) {
        viewModelScope.launch {
            settingManager.setCount(count - 1).onFailure { error ->
                _uiEvent.send(
                    UiEvent.ShowSnackbar("Khong the giam count: ${error.message}")
                )
            }
        }
    }
    data class DataStoreStateUI(
        val darkMode: Boolean = false,
        val language: String = "en",
        val username: String? = "",
        val tick: Boolean = false,
        val count: Int = 0
    )
}