package com.ibm.rides.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ibm.rides.R
import com.ibm.rides.VehicleViewModel
import com.ibm.rides.model.Vehicle

enum class SortOption { VIN, CAR_TYPE }

class FirstFragment : Fragment() {

    private val sharedViewModel: VehicleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ComposeView>(R.id.compose_view).setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(), // Set light color scheme
            ) {
                Surface(color = MaterialTheme.colorScheme.background) {
                    FirstScreen()
                }
            }
        }
    }

    @Composable
    fun FirstScreen() {
        var textInput by remember { mutableStateOf("") }
        var sortOption by remember { mutableStateOf(SortOption.VIN) }

        val vehicleList by sharedViewModel.vehicleList.observeAsState(emptyList())
        val apiError by sharedViewModel.apiError.observeAsState()
        val isLoading by sharedViewModel.isLoading.observeAsState(false)

        val sortedVehicleList = remember(sortOption, vehicleList) {
            when (sortOption) {
                SortOption.VIN -> vehicleList.sortedBy { it.vin }
                SortOption.CAR_TYPE -> vehicleList.sortedBy { it.car_type }
            }
        }

        // Display API Error if exists
        apiError?.let { sharedViewModel.clearError() }

        Surface(modifier = Modifier.fillMaxSize()) {
            if (isLoading) {
                LoadingIndicator()
            } else {
                VehicleListUI(
                    textInput = textInput,
                    onTextInputChange = { textInput = it },
                    onSearchClick = {
                        val size = textInput.toIntOrNull() ?: DEFAULT_SIZE
                        sharedViewModel.fetchVehiclesFromApi(size)
                    },
                    sortOption = sortOption,
                    onSortChange = { sortOption = it },
                    vehicleList = sortedVehicleList,
                    onVehicleClick = { vehicle ->
                        sharedViewModel.selectVehicle(vehicle)
                        actionRedirect()
                    }
                )
            }
        }
    }

    @Composable
    fun VehicleListUI(
        textInput: String,
        onTextInputChange: (String) -> Unit,
        onSearchClick: () -> Unit,
        sortOption: SortOption,
        onSortChange: (SortOption) -> Unit,
        vehicleList: List<Vehicle>,
        onVehicleClick: (Vehicle) -> Unit
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                SearchInputField(textInput, onTextInputChange)
            }

            item {
                SearchButton(onSearchClick)
            }

            item {
                SortOptionsRow(
                    sortOption = sortOption,
                    onSortChange = onSortChange
                )
            }

            items(vehicleList) { vehicle ->
                VehicleItem(vehicle = vehicle, onClick = { onVehicleClick(vehicle) })
            }
        }
    }

    @Composable
    fun SearchInputField(value: String, onValueChange: (String) -> Unit) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(stringResource(id = R.string.enter_number_of_items)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )
    }

    @Composable
    fun SearchButton(onClick: () -> Unit) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(ContextCompat.getColor(requireContext(), R.color.dark_grey))
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(id = R.string.Search))
        }
    }

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

    @Composable
    fun SortButton(selected: Boolean, onClick: () -> Unit, label: String) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selected)
                    Color(ContextCompat.getColor(requireContext(), R.color.dark_grey))
                else Color.White,
                contentColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface
            )
        ) {
            Text(label)
        }
    }

    @Composable
    fun VehicleItem(vehicle: Vehicle, onClick: () -> Unit) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable(onClick = onClick),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "${stringResource(id = R.string.make_and_model)} ${vehicle.make_and_model}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${stringResource(id = R.string.vin)} ${vehicle.vin}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f), thickness = 1.dp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${stringResource(id = R.string.car_type)} ${vehicle.car_type}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }

    @Composable
    fun LoadingIndicator() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

    private fun actionRedirect() {
        findNavController().navigate(R.id.action_startFragment_to_endFragment)
    }

    companion object {
        const val DEFAULT_SIZE = 2
    }
}
