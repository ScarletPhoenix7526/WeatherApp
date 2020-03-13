package com.example.weather.repository

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.weather.App
import com.example.weather.data.WeatherModel
import com.example.weather.network.ApiService
import com.example.weather.utils.FileLoggingTree
import com.example.weather.utils.ICON_URI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_weather_by_name.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepository {

    private var model: WeatherModel? = null
    private var service: ApiService? = null
    private var disposable: CompositeDisposable? = null

    private fun initComponents(context: Context) {
        disposable = CompositeDisposable()
        service = App().getAppInstance(context).getRetrofitService()
    }

    fun clearDisposable() {
        disposable?.clear()
    }

    fun getInfoByName(context: Context, location: String) {
        initComponents(context)
        disposable?.add(
            service!!.getWeatherByName(location)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(object : DisposableSingleObserver<WeatherModel>() {
                    override fun onSuccess(t: WeatherModel) {
                        RxBus.getInstance().successBoarding(t)
                    }

                    override fun onError(e: Throwable) {
                        RxBus.getInstance().errorBoarding(e)
                        FileLoggingTree().log(Log.DEBUG, e)
                    }
                })
        )
    }

    fun setWeatherIcon(icon: String, imageView: ImageView) {
        val uri = "$ICON_URI$icon.png"
        Glide.with(imageView.context).load(uri).into(imageView)
    }

    fun requestByCoordinates(context: Context, latitude: Float, longitude: Float) {
        service = App().getAppInstance(context).getRetrofitService()
        val call = service?.getWeatherByCoords(latitude, longitude)
        call?.enqueue(object : Callback<WeatherModel> {
            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {}

            override fun onResponse(call: Call<WeatherModel>, response: Response<WeatherModel>) {
                response.body()!!
            }
        })
    }

}