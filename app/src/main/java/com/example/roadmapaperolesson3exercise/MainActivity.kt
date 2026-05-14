package com.example.roadmapaperolesson3exercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.roadmapaperolesson3exercise.core.common.Resource
import com.example.roadmapaperolesson3exercise.ui.theme.RoadMapAperoLesson3ExerciseTheme
import com.example.roadmapaperolesson3exercise.view.general.RecipeApp
import com.example.roadmapaperolesson3exercise.viewmodel.DataStoreViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val dataStoreViewModel: DataStoreViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val dataStoreState by dataStoreViewModel.dataStoreState.collectAsStateWithLifecycle()
            val isDarkMode = when(val state = dataStoreState) {
                is Resource.Success -> state.data.darkMode
                else -> false
            }
            val navController = rememberNavController()
            RoadMapAperoLesson3ExerciseTheme(darkTheme = isDarkMode) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RecipeApp(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}