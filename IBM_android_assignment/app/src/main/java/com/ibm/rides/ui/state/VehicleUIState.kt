package com.ibm.rides.ui.state

import com.ibm.rides.data.model.domain.Vehicle

sealed class VehicleUIState {
    object Loading : VehicleUIState()
    data class Success(val vehicles: List<Vehicle>) : VehicleUIState()
    data class Error(val message: String) : VehicleUIState()
}
