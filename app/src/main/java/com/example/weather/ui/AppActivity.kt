package com.example.weather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.weather.R
import com.example.weather.ui.main.MainFragment
import com.example.weather.utils.BACK_STACK_MAIN

class AppActivity : AppCompatActivity() {

    var fragmentTransaction = supportFragmentManager.beginTransaction()
    private val startFragment = MainFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)
        initStartFragment()
    }

    private fun initStartFragment() {
        fragmentTransaction.apply {
            add(R.id.fragmentContainer, startFragment)
            addToBackStack(BACK_STACK_MAIN)
            commit()
        }
    }
}