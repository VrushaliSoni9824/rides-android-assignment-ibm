package com.ibm.rides

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibm.rides.api.ApiClient
import com.ibm.rides.model.Vehicle
import kotlinx.coroutines.launch

class VehicleViewModel : ViewModel() {

    // List of vehicles
    private val _vehicleList = MutableLiveData<List<Vehicle>>()
    val vehicleList: LiveData<List<Vehicle>> = _vehicleList

    // Loading state
    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    // Selected vehicle
    private val _selectedVehicle = MutableLiveData<Vehicle?>()
    val selectedVehicle: LiveData<Vehicle?> = _selectedVehicle

    // API error message
    private val _apiError = MutableLiveData<String?>()
    val apiError: LiveData<String?> = _apiError

    init {
        fetchVehiclesFromApi(2)
    }
    fun fetchVehiclesFromApi(size: Int) {
        _isLoading.value = true // Start loading
        viewModelScope.launch {
            try {
                val response = ApiClient.vehicleApiService.getVehicles(size)
                if (response.isSuccessful && response.body() != null) {
                    _vehicleList.value = response.body()!!
                } else {
                    // Handle the error response
                    _apiError.value = "Failed to load vehicles. Error: ${response.code()}"
                }
            } catch (e: Exception) {
                // Handle network errors
                _apiError.value = "Network error: ${e.message}"
            } finally {
                _isLoading.value = false // Stop loading
            }
        }
    }
    
    fun clearError() {
        _apiError.value = null
    }

    /** Set a new list of vehicles */
    fun setVehicleList(vehicles: List<Vehicle>) {
        _vehicleList.value = vehicles
    }

    /** Reset vehicle data (clear the list). */
    fun resetVehicleData() {
        _vehicleList.value = emptyList()
    }

    /** Select a vehicle */
    fun selectVehicle(vehicle: Vehicle) {
        _selectedVehicle.value = vehicle
    }

    /** Clear the selected vehicle */
    fun clearSelectedVehicle() {
        _selectedVehicle.value = null
    }
}
