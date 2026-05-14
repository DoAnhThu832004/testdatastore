package com.example.roadmapaperolesson3exercise.view.datastore.base

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable

@Composable
fun TickScreen(
    checked: Boolean,
    onCheckChange: (Boolean) -> Unit
) {
    Row() {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckChange
        )
    }
}