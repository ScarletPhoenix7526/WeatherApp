package com.example.weather.repository

import com.example.weather.data.WeatherModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RxBus {

    companion object {
        private var instance: RxBus? = null
        fun getInstance(): RxBus {
            if (instance == null) {
                instance = RxBus()
            }
            return instance!!
        }
    }

    var onSuccessPublisher: PublishSubject<WeatherModel> = PublishSubject.create()

    fun successBoarding(weather: WeatherModel) {
        onSuccessPublisher.onNext(weather)
    }

    fun successGettingOf(): Observable<WeatherModel> {
        return onSuccessPublisher
    }

    var onErrorPublisher: PublishSubject<Throwable> = PublishSubject.create()

    fun errorBoarding(t: Throwable) {
        onErrorPublisher.onNext(t)
    }

    fun errorGettingOf(): Observable<Throwable> {
        return onErrorPublisher
    }

}