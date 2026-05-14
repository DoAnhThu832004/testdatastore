package com.example.roadmapaperolesson3exercise.view.datastore.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.roadmapaperolesson3exercise.core.common.Resource
import com.example.roadmapaperolesson3exercise.core.common.UiEvent
import com.example.roadmapaperolesson3exercise.viewmodel.DataStoreViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SettingScreen(
    dataStoreViewModel: DataStoreViewModel = hiltViewModel()
) {
    val uiState by dataStoreViewModel.dataStoreState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = true) {
        dataStoreViewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(MaterialTheme.colorScheme.background)
    ) {
        when(val state = uiState) {
            is Resource.Loading -> {
                CircularProgressIndicator()
            }
            is Resource.Error -> {
                Text(
                    text = "Error: ${state.throwable.message}"
                )
            }
            is Resource.Success -> {
                val data = state.data
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    DarkModeScreen(
                        isDarkMode = data.darkMode,
                        onDarkModeChange = dataStoreViewModel::setDarkMode
                    )
                    HorizontalDivider()
                    LanguageScreen(
                        currentLanguage = data.language,
                        onLanguageSelected = dataStoreViewModel::setLanguage
                    )
                    HorizontalDivider()
                    UsernameScreen(
                        currentUsername = data.username ?: "Do Anh Thu",
                        onUsernameChange = dataStoreViewModel::setUserName
                    )
                    HorizontalDivider()
                    TickScreen(
                        checked = data.tick,
                        onCheckChange = dataStoreViewModel::setTick
                    )
                    HorizontalDivider()
                    CountScreen(
                        count = data.count,
                        onIncrement = dataStoreViewModel::setIncrementCount,
                        onDecrement = dataStoreViewModel::setDecrementCount
                    )
                }
            }
        }
    }
}
