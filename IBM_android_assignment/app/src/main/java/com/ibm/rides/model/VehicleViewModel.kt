package com.ibm.rides

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibm.rides.model.Vehicle
import com.ibm.rides.repository.VehicleRepository
import com.ibm.rides.sealedClass.VehicleUIState
import kotlinx.coroutines.launch

class VehicleViewModel(private val vehicleRepository: VehicleRepository) : ViewModel() {

    // List of vehicles
    private val _vehicleList = MutableLiveData<List<Vehicle>>()
    val vehicleList: LiveData<List<Vehicle>> = _vehicleList

    // Selected vehicle
    private val _selectedVehicle = MutableLiveData<Vehicle?>()
    val selectedVehicle: LiveData<Vehicle?> = _selectedVehicle

    // UI state (Loading, Success, Error)
    private val _uiState = MutableLiveData<VehicleUIState>(VehicleUIState.Loading)
    val uiState: LiveData<VehicleUIState> = _uiState

    private val _apiError = MutableLiveData<String?>()
    val apiError: LiveData<String?> = _apiError

    init {
        fetchVehiclesFromApi(DEFAULT_VEHICLE_COUNT)
    }

    // Fetch vehicles from API
    fun fetchVehiclesFromApi(size: Int) {
        if (size <= 0) {
            Log.e(TAG, "Invalid size parameter: $size. Must be greater than 0.")
            _uiState.value = VehicleUIState.Error("Invalid size parameter. Must be greater than 0.")
            return
        }

        _uiState.value = VehicleUIState.Loading // Start loading
        viewModelScope.launch {
            val result = vehicleRepository.fetchVehicles(size)
            result.onSuccess { vehicles ->
                _uiState.value = VehicleUIState.Success(vehicles)
            }.onFailure { error ->
                _uiState.value = VehicleUIState.Error("Network error: ${error.message}")
                Log.e(TAG, "Error fetching vehicles: ${error.message}", error)
            }
        }
    }

    /** Set a new list of vehicles */
    fun setVehicleList(vehicles: List<Vehicle>) {
        _vehicleList.value = vehicles
    }

    /** Reset vehicle data (clear the list). */
    fun resetVehicleData() {
        _vehicleList.value = emptyList() // Can be null if needed
    }

    /** Select a vehicle */
    fun selectVehicle(vehicle: Vehicle) {
        _selectedVehicle.value = vehicle
    }

    /** Clear the selected vehicle */
    fun clearSelectedVehicle() {
        _selectedVehicle.value = null
    }

    fun clearError() {
        _apiError.value = null
    }
    companion object {
        private const val TAG = "VehicleViewModel"
        private const val DEFAULT_VEHICLE_COUNT = 2
    }
}
