package com.ibm.rides.data.model.mapper

import com.ibm.rides.data.model.api.VehicleApiModel
import com.ibm.rides.data.model.domain.Vehicle

class VehicleMapper {
    fun mapToDomain(vehicleApiModel: VehicleApiModel): Vehicle {
        return Vehicle(
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

    fun mapToDomainList(vehicleApiModels: List<VehicleApiModel>): List<Vehicle> {
        return vehicleApiModels.map { mapToDomain(it) }
    }
}
