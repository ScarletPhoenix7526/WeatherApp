package com.example.weather.ui.info

import android.os.Bundle
import android.view.View
import com.example.weather.R
import com.example.weather.data.WeatherModel
import com.example.weather.repository.RxBus
import com.example.weather.ui.BaseFragment
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_weather_by_name.*

class InfoByNameFragment : BaseFragment(R.layout.fragment_weather_by_name) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog.show()
        getWeatherInfo()
        getErrorInfo()
        clearDisposable()
    }

    private fun getWeatherInfo() {
        RxBus.getInstance().successGettingOf().subscribe(weatherObserver())
    }

    private fun getErrorInfo() {
        RxBus.getInstance().errorGettingOf().subscribe(errorObserver())
    }

    private fun weatherObserver(): Observer<WeatherModel> {
        return object : Observer<WeatherModel> {
            override fun onComplete() {}
            override fun onError(e: Throwable) {}
            override fun onSubscribe(d: Disposable) {}

            override fun onNext(model: WeatherModel) {
                title.text = model.name
                repository.setWeatherIcon(model.weather[0].icon, weather_state_icon) //TODO: Synchronize it!
                loadingDialog.dismiss()
            }
        }
    }

    private fun errorObserver(): Observer<Throwable> {
        return object : Observer<Throwable> {
            override fun onComplete() {}
            override fun onError(e: Throwable) {}
            override fun onSubscribe(d: Disposable) {}

            override fun onNext(t: Throwable) {
                loadingDialog.dismiss()
                changeErrorFragment()
            }
        }
    }

    private fun clearDisposable() {
        repository.clearDisposable()
    }
}