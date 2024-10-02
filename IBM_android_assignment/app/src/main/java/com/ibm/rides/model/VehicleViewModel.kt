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

    private val _apiError = MutableLiveData<String?>()
    val apiError: LiveData<String?> = _apiError

    init {
        fetchVehiclesFromApi(2)
    }
    fun fetchVehiclesFromApi(size: Int) {
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
            }
        }
    }
    fun clearError() {
        _apiError.value = null
    }
    /**
        init {
        // Initialize with sample vehicle data
        val vehicles = listOf(
            Vehicle(
                vin = "1HGCM82633A004352",
                make_and_model = "Toyota Corolla",
                color = "Black",
                transmission = "Manual",
                drive_type = "FWD",
                fuel_type = "Ethanol",
                car_type = "Wagon",
                car_options = listOf("Tinted Glass", "Power Steering", "MP3 (Multi Disc)", "Airbag: Driver"),
                specs = listOf("625-amp maintenance-free battery", "Body color door handles"),
                doors = 3,
                mileage = 88019,
                kilometrage = 39731,
                license_plate = "ZSI-3244"
            ),Vehicle(
                vin = "1HGCM82633A004352",
                make_and_model = "Toyota Corolla",
                color = "Black",
                transmission = "Manual",
                drive_type = "FWD",
                fuel_type = "Ethanol",
                car_type = "Wagon",
                car_options = listOf("Tinted Glass", "Power Steering", "MP3 (Multi Disc)", "Airbag: Driver"),
                specs = listOf("625-amp maintenance-free battery", "Body color door handles"),
                doors = 3,
                mileage = 88019,
                kilometrage = 39731,
                license_plate = "ZSI-3244"
            ),
            Vehicle(
                vin = "1HGCM82633A004352",
                make_and_model = "Toyota Corolla",
                color = "Black",
                transmission = "Manual",
                drive_type = "FWD",
                fuel_type = "Ethanol",
                car_type = "Wagon",
                car_options = listOf("Tinted Glass", "Power Steering", "MP3 (Multi Disc)", "Airbag: Driver"),
                specs = listOf("625-amp maintenance-free battery", "Body color door handles"),
                doors = 3,
                mileage = 88019,
                kilometrage = 39731,
                license_plate = "ZSI-3244"
            ),
            Vehicle(
                vin = "7F7277H76CM976739",
                make_and_model = "Honda Accord",
                color = "Silver",
                transmission = "Automatic",
                drive_type = "AWD",
                fuel_type = "Gasoline",
                car_type = "Sedan",
                car_options = listOf("Power Windows", "Sunroof", "Air Conditioning", "Alloy Wheels"),
                specs = listOf("Eco Assist System", "Anti-lock Brakes"),
                doors = 4,
                mileage = 76000,
                kilometrage = 122300,
                license_plate = "ABC-1234"
            ),
        )

        // Set the vehicle list
        _vehicleList.value = vehicles
    }
    */

    /** Set a new list of vehicles */
    fun setVehicleList(vehicles: List<Vehicle>) {
        _vehicleList.value = vehicles
    }

    /** Reset vehicle data (clear the list). */
    fun resetVehicleData() {
        _vehicleList.value = emptyList()
    }
}