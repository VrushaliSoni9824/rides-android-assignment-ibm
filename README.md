# rides-android-assignment-ibm - Rides
Rides is a Kotlin-based Android app. It retrieves a list of vehicles from the Random Data API, displaying details like VIN, make and model. The app has  features like navigation, state management, input validation, sorting, and carbon emission calculations, with unit tests for validation and emissions logic.
 
## Features

- **Single Activity Architecture**: The app is designed using a single activity, ensuring smooth navigation between different screens.
- **Vehicle List Screen** (using Jetpack Compose):
  - Built using Jetpack Compose for modern UI development.
  - Input field for the number of vehicles to retrieve.
  - Button to fetch vehicles from the API.
  - Display of vehicles sorted by VIN, with an option to sort by car type.
  - Error handling for API failures.
- **Vehicle Details Screen** (XML with View Binding):
  - Built using traditional XML layouts with view binding for easy UI component access.
  - Clickable items in the Vehicle List navigate to a detailed view of the selected vehicle.
  - Displays vehicle details such as VIN, make and model, color, and car type.
- **Additional Features** (implemented):
  - Input validation for the number of vehicles to fetch.
  - Estimated carbon emissions based on vehicle kilometrage.
  - Unit tests for validation logic and carbon emissions calculation.

## Technologies Used

- **Kotlin**: Programming language used for Android development.
- **Android Studio**: IDE for Android development.
- **Coroutines**: For asynchronous programming.
- **Jetpack Compose**: For modern UI development in the Vehicle List screen.
- **XML Layouts**: For UI design in the Vehicle Details screen.
- **View Binding**: To access UI components easily.
- **Navigation Components**: For managing in-app navigation.

## API Used

The application retrieves vehicle data from the following API:
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
