package com.ibm.rides.ui.composables

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SortButton(selected: Boolean, onClick: () -> Unit, label: String) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected)
                MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.surface,
            contentColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
        )
    ) {
        Text(label)
    }
}
