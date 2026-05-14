package com.example.roadmapaperolesson3exercise.view.general

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.roadmapaperolesson3exercise.view.datastore.base.DataStoreScreen
import com.example.roadmapaperolesson3exercise.view.datastore.base.SettingScreen
import com.example.roadmapaperolesson3exercise.viewmodel.DataStoreViewModel

@Composable
fun RecipeApp(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SelectApp.route
    ) {
        composable(route = Screen.SelectApp.route) {
            SelectApp(
                onNavigateToDataStore = {
                    navController.navigate(Screen.DataStoreScreen.route)
                }
            )
        }
        navigation(
            startDestination = Screen.DataStoreScreen.route,
            route = Graph.DATA_STORE
        ) {
            composable(route = Screen.DataStoreScreen.route) {
                DataStoreScreen(
                    onNavigateToDarkModeAndLanguage = {
                        navController.navigate(Screen.SettingScreen.route)
                    }
                )
            }
            composable(route = Screen.SettingScreen.route) { backStackEntry ->
                val parentEntry = remember(backStackEntry) {
                    navController.getBackStackEntry(Graph.DATA_STORE)
                }
                val dataStoreViewModel = hiltViewModel<DataStoreViewModel>(parentEntry)
                SettingScreen(dataStoreViewModel)
            }
        }
    }
}