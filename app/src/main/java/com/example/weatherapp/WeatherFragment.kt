package com.example.weatherapp

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.weatherapp.viewmodels.LocationViewModel

class WeatherFragment : Fragment() {

    private val locViewModel: LocationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        locViewModel.locationLiveData.observe(viewLifecycleOwner){
            /*Toast.makeText(requireActivity(), "${it.latitude},${it.longitude}", Toast.LENGTH_SHORT).show()*/
            locViewModel.locationLiveData.observe(viewLifecycleOwner){
                locViewModel.fetchData()
            }
            locViewModel.currentModelLiveData.observe(viewLifecycleOwner){
                Log.d(ContentValues.TAG,"${it.main.temp}")
            }
            locViewModel.forecastModelLiveData.observe(viewLifecycleOwner){
                Log.d(ContentValues.TAG,"${it.list.size}")
            }
        }

        return inflater.inflate(R.layout.fragment_weather, container, false)
    }
}