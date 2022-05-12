package com.example.weatherapp

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.FragmentWeatherBinding
import com.example.weatherapp.models.CurrentModel
import com.example.weatherapp.networkpackage.getFormattedDate
import com.example.weatherapp.networkpackage.icon_prefix
import com.example.weatherapp.networkpackage.icon_suffix
import com.example.weatherapp.viewmodels.LocationViewModel
import kotlin.math.roundToInt

class WeatherFragment : Fragment() {

    private val locViewModel: LocationViewModel by activityViewModels()
    private lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        locViewModel.locationLiveData.observe(viewLifecycleOwner){
            /*Toast.makeText(requireActivity(), "${it.latitude},${it.longitude}", Toast.LENGTH_SHORT).show()*/
            locViewModel.locationLiveData.observe(viewLifecycleOwner){
                locViewModel.fetchData()
            }
            locViewModel.currentModelLiveData.observe(viewLifecycleOwner){
                setCurrentData(it)
            }
            locViewModel.forecastModelLiveData.observe(viewLifecycleOwner){
                Log.d(ContentValues.TAG,"${it.list.size}")
            }
        }

        return binding.root
    }

    private fun setCurrentData(it: CurrentModel) {
        binding.showDateTV.text = getFormattedDate(it.dt, "MMM dd, yyyy HH:mm")
        binding.showCityTV.text = "${it.name}, ${it.sys.country}"
        val iconUrl = "$icon_prefix${it.weather[0].icon}${icon_suffix}"
        Glide.with(requireActivity()).load(iconUrl).into(binding.showIconIV)
        binding.showTempTV.text = it.main.temp.roundToInt().toString()
        binding.showFeelTempTV.text = it.main.feels_like.roundToInt().toString()
        binding.showTempStatTV.text = it.weather[0].description
        binding.showMaxTempTV.text = it.main.temp_max.roundToInt().toString()
        binding.showMinTempTV.text = it.main.temp_min.roundToInt().toString()
        binding.showSunriseTV.text = getFormattedDate(it.sys.sunrise, "HH:mm")
        binding.showSunsetTV.text = getFormattedDate(it.sys.sunset, "HH:mm")

    }
}