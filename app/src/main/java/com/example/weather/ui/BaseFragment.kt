package com.example.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weather.R
import com.example.weather.repository.WeatherRepository
import com.example.weather.utils.BACK_STACK_FRAGMENT

abstract class BaseFragment(val layout: Int) : Fragment() {

    val repository by lazy { WeatherRepository() }
    val loadingDialog: LoadingDialog by lazy { LoadingDialog(context!!) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, null)
    }

    fun changeFragment(fragment: Fragment) {
        val manager = activity?.supportFragmentManager
        val fragmentTransaction = manager?.beginTransaction()!!
        fragmentTransaction.apply {
            replace(R.id.fragmentContainer, fragment)
            addToBackStack(BACK_STACK_FRAGMENT)
            commit()
        }
    }

    fun changeErrorFragment(fragment: Fragment) {
        val manager = activity?.supportFragmentManager
        val fragmentTransaction = manager?.beginTransaction()!!
        fragmentTransaction.apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
        }
    }
}