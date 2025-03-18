package com.example.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.weather.api.NetworkResponse
import com.example.weather.api.WeatherModel

@Composable
fun WeatherPage(weatherViewModel: WeatherViewModel) {
    var city by remember { mutableStateOf("") }
    val weatherResult = weatherViewModel.weatherResult.observeAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly){
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = city,
                onValueChange = {
                    city = it
                                },
                label = {
                    Text("Search for any Location", color = Color.Black)
                }
            )
            IconButton(onClick = {
                weatherViewModel.getData(city)
            }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search for any location",modifier =Modifier.size(50.dp))
            }
        }
        when(val result=weatherResult.value){
            is NetworkResponse.Error->{
                Text(text =result.message)
            }
            is NetworkResponse.Loading->{
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is NetworkResponse.Success->{
                WeatherDetails(result.data)
            }

            null -> {}
        }
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun WeatherDetails(data: WeatherModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Location Row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                modifier = Modifier.size(30.dp),
                tint = Color(0xFF1565C0) // Dark blue tint
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${data.location.name}, ${data.location.country}",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Temperature & Icon with Condition Text directly below
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "${data.current.temp_c}°C",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            GlideImage(
                model = "https:${data.current.condition.icon}",
                contentDescription = "Weather Icon",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(8.dp)) // Space between icon & condition text

            Text(
                text = data.current.condition.text,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Weather Info Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = androidx.compose.material3.CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = androidx.compose.material3.CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    WeatherInfoItem("Humidity", "${data.current.humidity}%")
                    WeatherInfoItem("Wind", "${data.current.wind_kph} km/h")
                    WeatherInfoItem("Pressure", "${data.current.pressure_mb} mb")
                }
                Spacer(modifier=Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    WeatherInfoItem("Dew point", "${data.current.dewpoString_c}")
                    WeatherInfoItem("Heat Index", "${data.current.heatindex_c}")
                    WeatherInfoItem("Gust", "${data.current.gust_kph} km/hr")
                }
            }
        }
    }
}

// Reusable Weather Info Item
@Composable
fun WeatherInfoItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color.Gray)
        Text(text = value, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black)
    }
}