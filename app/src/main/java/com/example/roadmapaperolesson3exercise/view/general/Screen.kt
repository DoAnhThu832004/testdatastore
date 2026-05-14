package com.example.roadmapaperolesson3exercise.view.general

sealed class Screen(val route: String) {
    object SelectApp : Screen("select_app")
    object DataStoreScreen : Screen("data_store_screen")
    object SettingScreen : Screen("setting_screen")
}