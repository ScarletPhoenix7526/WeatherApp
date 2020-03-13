package com.example.weather

import android.app.Application
import android.content.Context
import com.example.weather.network.ApiService
import com.example.weather.utils.FileLoggingTree
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit


class App : Application() {

    var service: ApiService? = null

    override fun onCreate() {
        super.onCreate()
        service = initRetrofit()
        Timber.plant(Timber.DebugTree())
        FileLoggingTree().saveLogCat()
    }

    private fun initRetrofit(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private fun getClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    fun getAppInstance(context: Context): App {
        return context.applicationContext as App
    }

    fun getRetrofitService(): ApiService? {
        return service
    }
}