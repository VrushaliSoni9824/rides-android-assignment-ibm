package com.ibm.rides.model
import com.google.gson.annotations.SerializedName

data class Vehicle(
    @SerializedName("vin") val vin: String,
    @SerializedName("make_and_model") val make_and_model: String,
    @SerializedName("color") val color: String,
    @SerializedName("transmission") val transmission: String,
    @SerializedName("drive_type") val drive_type: String,
    @SerializedName("fuel_type") val fuel_type: String,
    @SerializedName("car_type") val car_type: String,
    @SerializedName("car_options") val car_options: List<String>,
    @SerializedName("specs") val specs: List<String>,
    @SerializedName("doors") val doors: Int,
    @SerializedName("mileage") val mileage: Int,
    @SerializedName("kilometrage") val kilometrage: Int,
    @SerializedName("license_plate") val license_plate: String
)