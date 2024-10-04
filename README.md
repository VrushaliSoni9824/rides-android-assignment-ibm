# Rides - Android Assignment for IBM

Rides is a Kotlin-based Android app that retrieves and displays a list of vehicles from the Random Data API. It features smooth navigation, state management, input validation, sorting, carbon emission calculations, and network connectivity monitoring, along with unit tests for validation and emissions logic.

## Features

- **MVVM Clean Architecture**: The app is built using the Model-View-ViewModel (MVVM) architecture, ensuring separation of concerns and maintainability.
- **Sealed Class for UIState**: Managed smooth and silent error handling using a sealed class for representing different UI states, improving user experience and making error management more robust.
- **Single Activity Architecture**: The app uses a single activity design for seamless navigation between screens.

### Vehicle List Screen (Jetpack Compose)
- Built with Jetpack Compose for modern, declarative UI development.
- Input field to specify the number of vehicles to fetch.
- Button to fetch vehicles from the API.
- Displays the vehicle list sorted by VIN, with an option to sort by car type.
- **Error Handling with Retry**: When an API failure occurs, the app displays an error message "Something went wrong" along with a "Retry" button. Users can click on "Retry" to attempt fetching the data again. Once the issue is resolved (e.g., internet connection is restored or API becomes available), the app fetches and displays the data successfully.
  - *Future Enhancement*: Plan to implement saving the last successfully loaded data in a local database to load it when offline or in case of API failures for a smoother, silent fallback.

### Vehicle Details Screen (XML + View Binding)
- Developed with XML layouts and view binding for intuitive UI component management.
- Clickable vehicle list items navigate to a detailed view of the selected vehicle.
- Displays vehicle details such as VIN, make and model, color, and car type.

### Internet Connectivity Monitoring
- **Connectivity Alert with BroadcastReceiver**: The app integrates a feature that monitors internet connectivity using a **BroadcastReceiver**. When the internet connection is lost, the user is notified via a seekbar indicating the loss of connection, allowing for smooth handling of network issues. This ensures the user is aware of connectivity changes and can retry once the connection is restored.

### Additional Implemented Features
- Input validation for the number of vehicles to retrieve from the API.
- Carbon emissions estimation based on vehicle mileage.
- Unit tests for input validation and carbon emissions logic.

## Technologies Used

- **Kotlin**: Language for Android development.
- **Android Studio**: IDE used for development.
- **Coroutines**: For managing asynchronous tasks.
- **Jetpack Compose**: For UI development in the Vehicle List screen.
- **XML + View Binding**: For UI in the Vehicle Details screen.
- **Navigation Components**: For in-app navigation management.
- **MVVM**: Architecture pattern for organized, scalable code.
- **Sealed Class (UIState)**: For error handling and state management.
- **BroadcastReceiver**: For monitoring network state changes and alerting users of connectivity issues.

## API Used

Vehicle data is retrieved from:
- [Random Vehicle API](https://random-data-api.com/api/vehicle/random_vehicle)

## Getting Started

To run this project locally, follow these steps:

### Prerequisites

- Android Studio installed on your machine.
- Kotlin plugin enabled.

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/VrushaliSoni9824/rides-android-assignment-ibm
