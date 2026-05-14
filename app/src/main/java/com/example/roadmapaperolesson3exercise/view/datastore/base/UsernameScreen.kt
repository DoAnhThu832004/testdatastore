package com.example.roadmapaperolesson3exercise.view.datastore.base

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun UsernameScreen(
    currentUsername: String,
    onUsernameChange: (String) -> Unit
) {
    Row(
    ) {
        OutlinedTextField(
            value = currentUsername,
            onValueChange = onUsernameChange,
            label = { Text("Username") }
        )
    }
}