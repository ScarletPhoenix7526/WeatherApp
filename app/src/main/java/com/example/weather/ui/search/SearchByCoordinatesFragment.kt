package com.example.weather.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.weather.R
import com.example.weather.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_search_by_coordinates.*

class SearchByCoordinatesFragment : BaseFragment(R.layout.fragment_search_by_coordinates) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        clickGetWeather()
    }

    private fun clickGetWeather() {
        show_weather.setOnClickListener {
            getInfoByCoordinates()
        }
    }

    private fun getInfoByCoordinates() {
        repository.requestByCoordinates(
            context!!,
            latitude.text.toString().toFloat(),
            longitude.text.toString().toFloat()
        )
    }
}