package com.nester.openweathermap

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.nester.openweathermap.ViewModel.WeatherViewModel
import com.nester.openweathermap.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        weatherViewModel.getCityData()
        initListener()
        weatherViewModel.weatherResponse.observe(this, Observer { response ->

            val icon = "r${response.weather[0].icon}"
            val resID: Int = resources.getIdentifier(icon, "drawable", packageName)
            //Log.d("MainActivity", resID.toString())
            Glide.with(this)
                .load(resID)
                .into(binding.icon)

            binding.description.text = response.weather[0].description
            binding.name.text = response.name
            binding.speed.text = "${response.wind.speed}m/s"
            binding.temp.text = "${response.main.temp}°C"
            binding.humidity.text = "Humidity: ${response.main.humidity}%"
            binding.visibility.text = "Visibility: ${response.visibility}m"
            binding.pressure.text = "${response.main.pressure}hPa"

        })
    }

    @ExperimentalCoroutinesApi
    private fun initListener() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { weatherViewModel.setSearchQuery(it) }
                Log.d("main", "onQueryTextChange: $query")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        })
    }
}
