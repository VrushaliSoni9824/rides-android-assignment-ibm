package com.ibm.rides.data.network

import com.ibm.rides.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = BuildConfig.BASE_URL  // Replace with your base URL

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val vehicleApiService: VehicleApiService = retrofit.create(VehicleApiService::class.java)
}