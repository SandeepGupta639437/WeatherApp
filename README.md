# 🌦 Weather App
## 📌 Overview
The Weather App is a real-time weather application that allows users to check the weather conditions of any city they search for. The app provides accurate weather details, including temperature, humidity, and overall weather conditions, along with relevant icons. It fetches data dynamically using a weather API and presents it in a visually appealing and easy-to-understand format.

## ✨ Features
🔍 Search Functionality
Users can enter a city name in the search bar to retrieve current weather details for that location. The app sends a request to a weather API and displays the fetched data instantly.

### ⏳ Real-time Weather Data
Displays the current temperature in Celsius.
Shows weather conditions (e.g., Clear, Rainy, Cloudy).
Provides humidity levels to give users a complete weather overview.
### 🌤 Weather Icons
The app fetches and displays dynamic weather icons that represent the current condition (e.g., sun for clear skies, clouds for cloudy weather, rain for precipitation). These icons are updated based on API data.

### 🎨 Modern UI 
The UI is designed using Jetpack Compose, ensuring a smooth and responsive experience.
### 📱 User-friendly Interface
A clean and minimalistic design ensures a seamless user experience.
Weather data is presented in an organized and structured layout, making it easy to read.
The app features well-aligned cards with rounded corners to enhance the overall aesthetics.
## 🛠 Technologies Used
Jetpack Compose - Modern UI toolkit for building native Android interfaces.
Kotlin - Primary programming language for Android development.
LiveData & ViewModel - For efficient state management and data handling.
Weather API - Used to fetch real-time weather data.
Coil/Glide - For loading weather icons dynamically.
### 🚀 How It Works
User enters a city name in the search field.
The app fetches weather data using a weather API.
Displays real-time weather details, including temperature, humidity, and condition.
Shows relevant weather icons based on the retrieved data.
The UI adjusts according to light and dark mode settings for better usability.
