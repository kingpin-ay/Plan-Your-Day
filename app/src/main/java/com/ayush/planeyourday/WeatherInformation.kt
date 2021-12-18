package com.ayush.planeyourday

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.ayush.planeyourday.databinding.ActivityWeatherInformationBinding
import java.math.RoundingMode
import java.text.DecimalFormat


class WeatherInformation : AppCompatActivity() {
    lateinit var binding : ActivityWeatherInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val presentAddress ="Chakdaha"
        val destinationAddress = "Krishnanagar"
        loadDataPresentAddress(presentAddress)
        loadDataDestinationAddress(destinationAddress)
    }

    @SuppressLint("SetTextI18n")
    private fun loadDataDestinationAddress(cityName: String){
        val apiKey = "0e045f9e85f0ce7d713b6a576863d510"

        val queue = Volley.newRequestQueue(this)
        val url= "https://api.openweathermap.org/data/2.5/forecast?q=$cityName&appid=$apiKey"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            {response ->
                val weatherData  = response.getJSONArray("list").getJSONObject(0)
                val skyStatus = weatherData.getJSONArray("weather").getJSONObject(0).getString("main")

                when(skyStatus.lowercase()){
                    "clear" -> binding.weatherStatusPicture.setImageResource(R.drawable.sunny_weather_png)
                    "clouds" -> binding.weatherStatusPicture.setImageResource(R.drawable.clouds_weather_png)
                    "rain" ->  binding.weatherStatusPicture.setImageResource(R.drawable.thunder_strom_weather_png)
                    "snow" -> binding.weatherStatusPicture.setImageResource(R.drawable.snow_weather_png)
                    else -> binding.weatherStatusPicture.setImageResource(R.drawable.haze_weather_png)
                }
                val name = response.getJSONObject("city").getString("name")
                val temp = weatherData.getJSONObject("main").getDouble("temp") - 273.15
                val maxTemp = weatherData.getJSONObject("main").getDouble("temp_min") - 273.15
                val minTemp = weatherData.getJSONObject("main").getDouble("temp_max") - 273.15

                binding.destinationAddress.text = name.uppercase()
                binding.destinationAddressStatus.text = "Status : $skyStatus"
                binding.destinationAddressTemp.text = "Temp : ${roundOffDecimal(temp)}° C"
                binding.destinationAddressMaxTemp.text = "Max Temp : ${roundOffDecimal(maxTemp)}° C"
                binding.destinationAddressMinTemp.text = "Min Temp : ${roundOffDecimal(minTemp)}° C"
                binding.leftSideDestinationView.text = name.uppercase()
                binding.leftSideDestinationDescription.text = "Temp : ${roundOffDecimal(temp)}° C"
                binding.leftSideDestinationDescriptionStatus.text = "Status : $skyStatus"


            },
            {
                Toast.makeText(this, "There is an issue with the weather data try again later", Toast.LENGTH_SHORT).show()
            }
        )

        queue.add(jsonObjectRequest)
    }

    @SuppressLint("SetTextI18n")
    private fun loadDataPresentAddress(cityName : String){
        val apiKey = "0e045f9e85f0ce7d713b6a576863d510"

        val queue = Volley.newRequestQueue(this)
        val url= "https://api.openweathermap.org/data/2.5/forecast?q=$cityName&appid=$apiKey"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            {response ->
                val weatherData  = response.getJSONArray("list").getJSONObject(0)
                val skyStatus = weatherData.getJSONArray("weather").getJSONObject(0).getString("main")
                val name = response.getJSONObject("city").getString("name")
                val temp = weatherData.getJSONObject("main").getDouble("temp") - 273.15
                val maxTemp = weatherData.getJSONObject("main").getDouble("temp_min") - 273.15
                val minTemp = weatherData.getJSONObject("main").getDouble("temp_max") - 273.15

                binding.presentAddress.text = name.uppercase()
                binding.presentAddressStatus.text = "Status : $skyStatus"
                binding.presentAddressTemp.text = "Temp : ${roundOffDecimal(temp)}° C"
                binding.presentAddressMaxTemp.text = "Max Temp : ${roundOffDecimal(maxTemp)}° C"
                binding.presentAddressMinTemp.text = "Min Temp : ${roundOffDecimal(minTemp)}° C"


            },
            {
                Toast.makeText(this, "There is an issue with the weather data try again later", Toast.LENGTH_SHORT).show()
            }
        )

        queue.add(jsonObjectRequest)

    }

    fun roundOffDecimal(number: Double): Double {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.FLOOR
        return df.format(number).toDouble()
    }

}