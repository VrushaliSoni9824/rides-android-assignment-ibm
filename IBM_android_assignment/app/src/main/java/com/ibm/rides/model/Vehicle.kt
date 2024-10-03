package com.ibm.rides.model

import com.google.gson.annotations.SerializedName

data class Vehicle(
    val vin: String,
    val make_and_model: String,
    val color: String,
    val transmission: String,
    val drive_type: String,
    val fuel_type: String,
    val car_type: String,
    val car_options: List<String>,
    val specs: List<String>,
    val doors: Int,
    val mileage: Int,
    val kilometrage: Int,
    val license_plate: String
) {
    fun calculateCarbonEmissions(): Double {
        return when {
            kilometrage <= 5000 -> kilometrage * 1.0 // 1 unit of carbon per km for the first 5000 km
            else -> (5000 * 1.0) + ((kilometrage - 5000) * 1.5) // 1 unit for first 5000 km, 1.5 for remaining km
        }
    }
}
