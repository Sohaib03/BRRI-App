# BRRI Rice Species App

Welcome to the BRRI Rice Species App, an Android application developed in Java! This app is tailored for the Bangladesh Rice Research Institute (BRRI) and allows users to explore and analyze various rice species based on different tolerance scores. Whether you're searching for specific species, sorting by tolerance, or just curious about rice varieties, this app has got you covered.

## Features

- **Search Functionality:** Easily find rice species by name, ID, or other criteria.
- **Sorting Options:** Sort rice species based on salinity, submergence, cold tolerance, drought, and more.
- **Detailed Information:** Access comprehensive details about each rice species, including tolerance scores.

## Usage

1. **Install the App:** Download and install the BRRI Rice Species App on your Android device.
2. **Explore Species:** Use the search and sorting features to delve into different rice species.
3. **View Details:** Tap on a specific species to reveal detailed information and tolerance scores.

## RiceSpecies Class Overview

The core functionality of the app is powered by the `RiceSpecies` class, acting as a structured container for information about each rice variety. It simplifies the retrieval and manipulation of data related to rice species.

### What's Inside?

#### Properties
- **Name:** The name of the rice species.
- **ID:** A unique identifier for each species.
- **Tolerance Scores:**
  - **Submergence:** Indicates how well the rice species can withstand being submerged in water.
  - **Salinity:** Measures the rice species' ability to thrive in saline or salty conditions.
  - **Drought:** Reflects the species' resilience to drought conditions.
  - **Cold Tolerance:** Gauges the rice species' capacity to endure colder climates.

#### Constants for Tolerance Types
To keep things organized, we use constants representing different tolerance types. These constants make it easy to refer to specific aspects when working with the app's functionality.

- **1. SUBMERGENCE**
- **2. SALINITY**
- **3. ID**
- **4. DROUGHT**
- **5. COLD TOLERANCE**
- **6. NAME**

### How It Works
When you use the app to search, sort, or view details, the `RiceSpecies` class provides the necessary information. It acts like a well-organized filing cabinet, simplifying the app's operations and ensuring a smooth user experience.

Feel free to explore the diverse world of rice species with ease, and let the `RiceSpecies` class handle the behind-the-scenes work for you!

## Contributing

If you'd like to contribute to the development of this app, please follow these steps:

1. Fork the repository.
2. Create a new branch for your feature or bug fix.
3. Implement your changes.
4. Submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries or support, please contact the BRRI development team at [dev@example.com](mailto:dev@example.com).
