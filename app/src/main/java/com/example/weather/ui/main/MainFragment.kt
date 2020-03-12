package com.example.weather.ui.main

import android.os.Bundle
import android.view.View
import com.example.weather.R
import com.example.weather.ui.BaseFragment
import com.example.weather.ui.search.SearchByCoordinatesFragment
import com.example.weather.ui.search.SearchByNameFragment
import com.example.weather.utils.BACK_STACK_FRAGMENT
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : BaseFragment(R.layout.fragment_main) {

    private val searchByNameFragment = SearchByNameFragment()
    private val searchByCoordsFragment = SearchByCoordinatesFragment()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        clickSearchByName()
        clickSearchByCoordinates()
    }

    private fun clickSearchByName() {
        name.setOnClickListener {
            changeFragment(searchByNameFragment)
        }
    }

    private fun clickSearchByCoordinates() {
        coordinates.setOnClickListener {
            changeFragment(searchByCoordsFragment)
        }
    }
}