package com.ibm.rides.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ibm.rides.R
import com.ibm.rides.ui.fragments.SortOption

@Composable
fun SortOptionsRow(sortOption: SortOption, onSortChange: (SortOption) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SortButton(
            selected = sortOption == SortOption.VIN,
            onClick = { onSortChange(SortOption.VIN) },
            label = stringResource(id = R.string.sort_by_vin)
        )
        SortButton(
            selected = sortOption == SortOption.CAR_TYPE,
            onClick = { onSortChange(SortOption.CAR_TYPE) },
            label = stringResource(id = R.string.sort_by_car_type)
        )
    }
}
