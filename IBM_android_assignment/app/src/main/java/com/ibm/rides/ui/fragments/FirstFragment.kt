package com.ibm.rides.ui.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ibm.rides.R
import com.ibm.rides.data.factory.VehicleViewModelFactory
import com.ibm.rides.viewmodel.VehicleViewModel
import com.ibm.rides.data.repository.VehicleRepository
import com.ibm.rides.ui.composable.ErrorUI
import com.ibm.rides.ui.composables.VehicleListUI
import com.ibm.rides.ui.state.VehicleUIState
import com.ibm.rides.ui.theme.RidesTheme
import com.ibm.rides.utils.NetworkUtils

enum class SortOption { VIN, CAR_TYPE }

class FirstFragment : Fragment() {

    private val sharedViewModel: VehicleViewModel by activityViewModels {
        VehicleViewModelFactory(VehicleRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ComposeView>(R.id.compose_view).setContent {
            RidesTheme {
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
        var errorMessage by remember { mutableStateOf<String?>(null) }

        val vehicleUIState by sharedViewModel.uiState.observeAsState(VehicleUIState.Loading)

        when (vehicleUIState) {
            is VehicleUIState.Loading -> LoadingIndicator()
            is VehicleUIState.Success -> {
                val vehicleList = (vehicleUIState as VehicleUIState.Success).vehicles
                VehicleListUI(
                    textInput = textInput,
                    onTextInputChange = {
                        textInput = it
                        errorMessage = null
                    },
                    onSearchClick = {
                        val size = textInput.toIntOrNull()
                        if (NetworkUtils.isInputValid(size)) {
                            sharedViewModel.fetchVehiclesFromApi(size!!)
                        } else {
                            errorMessage = "Please enter a number between 1 and 100."
                        }
                    },
                    sortOption = sortOption,
                    onSortChange = { sortOption = it },
                    vehicleList = vehicleList.sortedBy {
                        when (sortOption) {
                            SortOption.VIN -> it.vin
                            SortOption.CAR_TYPE -> it.car_type
                        }
                    },
                    onVehicleClick = { vehicle ->
                        sharedViewModel.selectVehicle(vehicle)
                        actionRedirect()
                    },
                    errorMessage = errorMessage
                )
            }
            is VehicleUIState.Error -> {
                val errorMessage = (vehicleUIState as VehicleUIState.Error).message
                ErrorUI(errorMessage) {
                    val size = textInput.toIntOrNull() ?: DEFAULT_SIZE
                    sharedViewModel.fetchVehiclesFromApi(size)
                }
            }
        }
    }

    @Composable
    fun LoadingIndicator() {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    private fun actionRedirect() {
        findNavController().navigate(R.id.action_startFragment_to_endFragment)
    }

    companion object {
        private const val DEFAULT_SIZE = 10
    }
}
