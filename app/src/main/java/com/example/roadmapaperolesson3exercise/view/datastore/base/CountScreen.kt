package com.example.roadmapaperolesson3exercise.view.datastore.base

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CountScreen(
    count: Int,
    onIncrement: (Int) -> Unit,
    onDecrement: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { onIncrement(count) }
        ) {
            Text(
                text = "+"
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = count.toString()
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { onDecrement(count) }
        ) {
            Text(
                text = "-"
            )
        }
    }
}