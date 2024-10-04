package com.ibm.rides.data.model.domain
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
)
