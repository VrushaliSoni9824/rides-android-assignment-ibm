package com.ibm.rides.api

//import com.ibm.rides.Vehicle
import com.ibm.rides.model.Vehicle
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VehicleApiService {
    @GET("api/vehicle/random_vehicle?size=2")  // Replace with your actual endpoint
    suspend fun getVehicles(@Query("size") size: Int): Response<List<Vehicle>>
}
