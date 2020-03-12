package com.example.weather.ui.info

import android.os.Bundle
import android.view.View
import com.example.weather.R
import com.example.weather.data.WeatherModel
import com.example.weather.repository.RxBus
import com.example.weather.ui.BaseFragment
import com.example.weather.ui.search.SearchByNameFragment
import com.example.weather.utils.BACK_STACK_ERROR
import com.example.weather.utils.ICON_URI
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
                setWeatherIcon(model.weather[0].icon) //TODO: Synchronize it!
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
                changeErrorFragment(SearchByNameFragment())
            }
        }
    }

    private fun clearDisposable() {
        repository.clearDisposable()
    }

    fun setWeatherIcon(icon: String) {
        val uri = "$ICON_URI$icon.png"
        weather_state_icon.setImageURI(uri)
    }
}