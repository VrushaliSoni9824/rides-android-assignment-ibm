package com.ibm.rides.api

import com.ibm.rides.Vehicle
import retrofit2.Response
import retrofit2.http.GET

interface VehicleApiService {
    @GET("api/vehicle/random_vehicle?size=2")  // Replace with your actual endpoint
    suspend fun getVehicles(): Response<List<Vehicle>>
}
