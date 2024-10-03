package com.ibm.rides.repository

import android.util.Log
import com.ibm.rides.api.ApiClient
import com.ibm.rides.api.VehicleApiModel
import com.ibm.rides.model.Vehicle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class VehicleRepository {

    suspend fun fetchVehicles(size: Int): Result<List<Vehicle>> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<List<VehicleApiModel>> = ApiClient.vehicleApiService.getVehicles(size)
                if (response.isSuccessful && response.body() != null) {
                    val vehicles = response.body()!!.map { vehicleApiModel ->
                        Vehicle(
                            vin = vehicleApiModel.vin,
                            make_and_model = vehicleApiModel.make_and_model,
                            car_type = vehicleApiModel.car_type,
                            color = vehicleApiModel.color,
                            drive_type = vehicleApiModel.drive_type,
                            car_options = vehicleApiModel.car_options,
                            doors = vehicleApiModel.doors,
                            fuel_type = vehicleApiModel.fuel_type,
                            kilometrage = vehicleApiModel.kilometrage,
                            license_plate = vehicleApiModel.license_plate,
                            mileage = vehicleApiModel.mileage,
                            specs = vehicleApiModel.specs,
                            transmission = vehicleApiModel.transmission
                        )
                    }
                    Result.success(vehicles)
                } else {
                    Result.failure(Exception("Failed to load vehicles. Error: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    companion object {
        private const val TAG = "VehicleRepository"
    }
}
