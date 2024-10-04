package com.ibm.rides.model

import com.ibm.rides.data.model.domain.Vehicle
import org.junit.Assert.assertEquals
import org.junit.Test

class VehicleTest {

    @Test
    fun testCalculateCarbonEmissions_WithKilometrageLessThanOrEqualTo5000() {
        // Arrange
        val vehicle = Vehicle(
            vin = "1HGCM82633A123456",
            make_and_model = "Honda Accord",
            color = "Red",
            transmission = "Automatic",
            drive_type = "FWD",
            fuel_type = "Gasoline",
            car_type = "Sedan",
            car_options = listOf("Air Conditioning", "Navigation"),
            specs = listOf("V6", "Leather Seats"),
            doors = 4,
            mileage = 25,
            kilometrage = 4000, // Test with kilometrage less than 5000
            license_plate = "ABC123"
        )

        // Act
        val emissions = vehicle.calculateCarbonEmissions()

        // Assert
        assertEquals(4000.0, emissions, 0.001) // Expect 1 unit per km
    }

    @Test
    fun testCalculateCarbonEmissions_WithKilometrageGreaterThan5000() {
        // Arrange
        val vehicle = Vehicle(
            vin = "1HGCM82633A123456",
            make_and_model = "Honda Accord",
            color = "Red",
            transmission = "Automatic",
            drive_type = "FWD",
            fuel_type = "Gasoline",
            car_type = "Sedan",
            car_options = listOf("Air Conditioning", "Navigation"),
            specs = listOf("V6", "Leather Seats"),
            doors = 4,
            mileage = 25,
            kilometrage = 10000, // Test with kilometrage greater than 5000
            license_plate = "ABC123"
        )

        // Act
        val emissions = vehicle.calculateCarbonEmissions()

        // Assert
        assertEquals(9250.0, emissions, 0.001) // Expect 5000 * 1.0 + (10000 - 5000) * 1.5
    }

    @Test
    fun testCalculateCarbonEmissions_WithKilometrageExactly5000() {
        // Arrange
        val vehicle = Vehicle(
            vin = "1HGCM82633A123456",
            make_and_model = "Honda Accord",
            color = "Red",
            transmission = "Automatic",
            drive_type = "FWD",
            fuel_type = "Gasoline",
            car_type = "Sedan",
            car_options = listOf("Air Conditioning", "Navigation"),
            specs = listOf("V6", "Leather Seats"),
            doors = 4,
            mileage = 25,
            kilometrage = 5000, // Test with kilometrage exactly 5000
            license_plate = "ABC123"
        )

        // Act
        val emissions = vehicle.calculateCarbonEmissions()

        // Assert
        assertEquals(5000.0, emissions, 0.001) // Expect 1 unit per km
    }
    // Fail unit test
//    @Test
//    fun testCalculateCarbonEmissions_FailedTest() {
//        // Arrange
//        val vehicle = Vehicle(
//            vin = "1HGCM82633A123456",
//            make_and_model = "Honda Accord",
//            color = "Red",
//            transmission = "Automatic",
//            drive_type = "FWD",
//            fuel_type = "Gasoline",
//            car_type = "Sedan",
//            car_options = listOf("Air Conditioning", "Navigation"),
//            specs = listOf("V6", "Leather Seats"),
//            doors = 4,
//            mileage = 25,
//            kilometrage = 10000, // Test with kilometrage greater than 5000
//            license_plate = "ABC123"
//        )
//
//        // Act
//        val emissions = vehicle.calculateCarbonEmissions()
//
//        // Assert
//        assertEquals(8000.0, emissions, 0.001) // Intentionally incorrect expectation
//    }

}
