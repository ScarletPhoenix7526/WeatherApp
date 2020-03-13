package com.example.weather.ui.search

import android.os.Bundle
import android.util.Log
import com.example.weather.R
import com.example.weather.data.WeatherModel
import com.example.weather.ui.BaseFragment
import com.example.weather.ui.info.InfoByNameFragment
import com.example.weather.utils.BACK_STACK_FRAGMENT
import com.example.weather.utils.FileLoggingTree
import kotlinx.android.synthetic.main.fragment_search_by_name.*
import timber.log.Timber

class SearchByNameFragment : BaseFragment(R.layout.fragment_search_by_name){

    val infoWeatherFragment = InfoByNameFragment()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        clickGetWeather()
    }

    private fun clickGetWeather() {
        show_weather.setOnClickListener {
            getInfoByName()
            changeFragment(infoWeatherFragment)
        }
    }

    private fun getInfoByName() {
        repository.getInfoByName(context!!, location.text.toString())
    }
}