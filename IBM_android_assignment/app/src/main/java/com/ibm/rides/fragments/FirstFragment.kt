package com.ibm.rides.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ibm.rides.R
import com.ibm.rides.Vehicle
import com.ibm.rides.VehicleViewModel

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
        val composeView = view.findViewById<ComposeView>(R.id.compose_view)
        composeView.setContent {
            MaterialTheme {
                FirstScreen()
            }
        }
    }

    @Composable
    fun FirstScreen() {
        var textInput by remember { mutableStateOf("") }
        var sortOption by remember { mutableStateOf(SortOption.VIN) }

        val vehicleList by sharedViewModel.vehicleList.observeAsState(emptyList())

        // Sort vehicle list based on selected option
        val sortedVehicleList = when (sortOption) {
            SortOption.VIN -> vehicleList.sortedBy { it.vin }
            SortOption.CAR_TYPE -> vehicleList.sortedBy { it.car_type }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                TextField(
                    value = textInput,
                    onValueChange = { textInput = it },
                    label = { Text(stringResource(id = R.string.enter_number_of_items)) },  // Use stringResource here
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
            }

            item {
                Button(
                    onClick = { actionRedirect() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(id = R.string.Search))
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    SortButton(
                        selected = sortOption == SortOption.VIN,
                        onClick = { sortOption = SortOption.VIN },
                        label = stringResource(id = R.string.sort_by_vin)
                    )

                    SortButton(
                        selected = sortOption == SortOption.CAR_TYPE,
                        onClick = { sortOption = SortOption.CAR_TYPE },
                        label = stringResource(id = R.string.sort_by_car_type)
                    )
                }
            }

            items(sortedVehicleList) { vehicle ->
                VehicleItem(vehicle = vehicle) {
                    actionRedirect()
                }
            }
        }
    }

    @Composable
    fun SortButton(selected: Boolean, onClick: () -> Unit, label: String) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
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
                .padding(vertical = 4.dp)
                .clickable(onClick = onClick),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "${stringResource(id = R.string.make_and_model)} ${vehicle.make_and_model}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "${stringResource(id = R.string.vin)} ${vehicle.vin}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "${stringResource(id = R.string.car_type)} ${vehicle.car_type}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }

    private fun actionRedirect() {
        findNavController().navigate(R.id.action_startFragment_to_endFragment)
    }
}
