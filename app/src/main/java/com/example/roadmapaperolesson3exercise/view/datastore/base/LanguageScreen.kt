package com.example.roadmapaperolesson3exercise.view.datastore.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.roadmapaperolesson3exercise.R

@Composable
fun LanguageScreen(
    currentLanguage: String,
    onLanguageSelected: (String) -> Unit
) {
    Column(
    ) {
        Text(
            text = stringResource(R.string.language),
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        FilterChip(
            selected = currentLanguage == "en",
            onClick = { onLanguageSelected("en") },
            label = { Text("English") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = MaterialTheme.colorScheme.secondary,
                selectedLabelColor = MaterialTheme.colorScheme.onSecondary
            )
        )
        FilterChip(
            selected = currentLanguage == "vi",
            onClick = { onLanguageSelected("vi") },
            label = { Text("Vietnamese") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = MaterialTheme.colorScheme.secondary,
                selectedLabelColor = MaterialTheme.colorScheme.onSecondary
            )
        )
    }
}