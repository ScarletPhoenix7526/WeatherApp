package com.example.weather.utils

import com.example.weather.BuildConfig

const val ENDPOINT_CURRENT_WEATHER = "data/2.5/weather?appid=${BuildConfig.API_KEY}"
const val ICON_URI = "http://openweathermap.org/img/wn/"

const val BACK_STACK_MAIN = "back_stack_main"
const val BACK_STACK_FRAGMENT = "back_stack_fragment"