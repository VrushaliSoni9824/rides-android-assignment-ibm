package com.ibm.rides

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class VehicleViewModel : ViewModel() {

    // Vehicle Identification Number (VIN)
    private val _vin = MutableLiveData<String>()
    val vin: LiveData<String> = _vin

    // Make and Model of the vehicle
    private val _makeAndModel = MutableLiveData<String>()
    val makeAndModel: LiveData<String> = _makeAndModel

    // Color of the vehicle
    private val _color = MutableLiveData<String>()
    val color: LiveData<String> = _color

    // Transmission type of the vehicle
    private val _transmission = MutableLiveData<String>()
    val transmission: LiveData<String> = _transmission

    // Drive type of the vehicle
    private val _driveType = MutableLiveData<String>()
    val driveType: LiveData<String> = _driveType

    // Fuel type of the vehicle
    private val _fuelType = MutableLiveData<String>()
    val fuelType: LiveData<String> = _fuelType

    // Car type (e.g., Wagon, Sedan, etc.)
    private val _carType = MutableLiveData<String>()
    val carType: LiveData<String> = _carType

    // Car options (e.g., Power Steering, DVD System, etc.)
    private val _carOptions = MutableLiveData<List<String>>()
    val carOptions: LiveData<List<String>> = _carOptions

    // Car specifications (e.g., Battery capacity, Rear bench seat, etc.)
    private val _specs = MutableLiveData<List<String>>()
    val specs: LiveData<List<String>> = _specs

    // Number of doors
    private val _doors = MutableLiveData<Int>()
    val doors: LiveData<Int> = _doors

    // Mileage in miles
    private val _mileage = MutableLiveData<Int>()
    val mileage: LiveData<Int> = _mileage

    // Kilometrage in kilometers
    private val _kilometrage = MutableLiveData<Int>()
    val kilometrage: LiveData<Int> = _kilometrage

    // License plate
    private val _licensePlate = MutableLiveData<String>()
    val licensePlate: LiveData<String> = _licensePlate

    init {
        // Initialize the vehicle data with the values
        setVehicleData(
            vin = "7F7277H76CM976739",
            makeAndModel = "Toyota Corolla",
            color = "Black",
            transmission = "Manual",
            driveType = "FWD",
            fuelType = "Ethanol",
            carType = "Wagon",
            carOptions = listOf("Tinted Glass", "Power Steering", "MP3 (Multi Disc)", "Airbag: Driver", "Premium Sound", "DVD System", "DVD System"),
            specs = listOf("625-amp maintenance-free battery", "Body color door handles", "Rear bench seat -inc: (3) adjustable headrests", "Silver finish interior door handles", "Cargo area lamp"),
            doors = 3,
            mileage = 88019,
            kilometrage = 39731,
            licensePlate = "ZSI-3244"
        )
    }

    /**
     * Set the vehicle data with the parameters.
     */
    fun setVehicleData(
        vin: String,
        makeAndModel: String,
        color: String,
        transmission: String,
        driveType: String,
        fuelType: String,
        carType: String,
        carOptions: List<String>,
        specs: List<String>,
        doors: Int,
        mileage: Int,
        kilometrage: Int,
        licensePlate: String
    ) {
        _vin.value = vin
        _makeAndModel.value = makeAndModel
        _color.value = color
        _transmission.value = transmission
        _driveType.value = driveType
        _fuelType.value = fuelType
        _carType.value = carType
        _carOptions.value = carOptions
        _specs.value = specs
        _doors.value = doors
        _mileage.value = mileage
        _kilometrage.value = kilometrage
        _licensePlate.value = licensePlate
    }

    /**
     * Reset the vehicle data to default empty values.
     */
    fun resetVehicleData() {
        setVehicleData(
            vin = "",
            makeAndModel = "",
            color = "",
            transmission = "",
            driveType = "",
            fuelType = "",
            carType = "",
            carOptions = emptyList(),
            specs = emptyList(),
            doors = 0,
            mileage = 0,
            kilometrage = 0,
            licensePlate = ""
        )
    }
}