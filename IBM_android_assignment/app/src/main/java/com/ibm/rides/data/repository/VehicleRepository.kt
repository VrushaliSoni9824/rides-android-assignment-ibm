package com.ibm.rides.data.repository

import com.ibm.rides.data.network.ApiClient
import com.ibm.rides.data.model.api.VehicleApiModel
import com.ibm.rides.data.model.domain.Vehicle
import com.ibm.rides.data.model.mapper.VehicleMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class VehicleRepository {

    private val vehicleMapper = VehicleMapper()

    suspend fun fetchVehicles(size: Int): Result<List<Vehicle>> {
        return withContext(Dispatchers.IO) {
            try {
                val response: Response<List<VehicleApiModel>> = ApiClient.vehicleApiService.getVehicles(size)
                if (response.isSuccessful && response.body() != null) {
                    val vehicles = vehicleMapper.mapToDomainList(response.body()!!)
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