package com.ibm.rides.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.ibm.rides.R
import com.ibm.rides.data.model.domain.Vehicle
import com.ibm.rides.ui.fragments.SortOption

@Composable
fun VehicleListUI(
    textInput: String,
    onTextInputChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    sortOption: SortOption,
    onSortChange: (SortOption) -> Unit,
    vehicleList: List<Vehicle>,
    onVehicleClick: (Vehicle) -> Unit,
    errorMessage: String?
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Row(modifier = Modifier.padding(bottom = 8.dp)) {
                Text(
                    text = stringResource(id = R.string.vehicle_list),
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.weight(1f)
                )
                if (isLandscape) {
                    Text(
                        text = "Scroll down to view vehicle list.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            textDecoration = TextDecoration.Underline,
                            fontStyle = FontStyle.Italic
                        ),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
            SearchInputField(textInput, onTextInputChange)
            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            SearchButton(onSearchClick)
            Spacer(modifier = Modifier.height(16.dp))
            SortOptionsRow(sortOption, onSortChange)
        }
        items(vehicleList) { vehicle ->
            VehicleItem(vehicle, onClick = { onVehicleClick(vehicle) })
        }
    }
}
