package com.ibm.rides.data.network

//import com.ibm.rides.Vehicle
import com.ibm.rides.data.model.api.VehicleApiModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VehicleApiService {
    @GET("api/vehicle/random_vehicle")
    suspend fun getVehicles(@Query("size") size: Int): Response<List<VehicleApiModel>>
}
