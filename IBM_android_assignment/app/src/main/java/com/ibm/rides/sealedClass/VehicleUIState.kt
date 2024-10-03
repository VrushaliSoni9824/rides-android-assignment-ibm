package com.ibm.rides.sealedClass

import com.ibm.rides.model.Vehicle

sealed class VehicleUIState {
    object Loading : VehicleUIState()
    data class Success(val vehicles: List<Vehicle>) : VehicleUIState()
    data class Error(val message: String) : VehicleUIState()
}
