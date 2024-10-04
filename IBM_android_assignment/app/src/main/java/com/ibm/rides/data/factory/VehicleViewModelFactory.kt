package com.ibm.rides.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ibm.rides.data.repository.VehicleRepository
import com.ibm.rides.viewmodel.VehicleViewModel

class VehicleViewModelFactory(private val repository: VehicleRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VehicleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VehicleViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}