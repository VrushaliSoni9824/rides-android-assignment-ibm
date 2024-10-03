package com.ibm.rides.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VehicleApiService {
    @GET("api/vehicle/random_vehicle")
    suspend fun getVehicles(@Query("size") size: Int): Response<List<VehicleApiModel>>
}
