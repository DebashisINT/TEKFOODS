package com.tekfoods.features.weather.api

import com.tekfoods.base.BaseResponse
import com.tekfoods.features.task.api.TaskApi
import com.tekfoods.features.task.model.AddTaskInputModel
import com.tekfoods.features.weather.model.ForeCastAPIResponse
import com.tekfoods.features.weather.model.WeatherAPIResponse
import io.reactivex.Observable

class WeatherRepo(val apiService: WeatherApi) {
    fun getCurrentWeather(zipCode: String): Observable<WeatherAPIResponse> {
        return apiService.getTodayWeather(zipCode)
    }

    fun getWeatherForecast(zipCode: String): Observable<ForeCastAPIResponse> {
        return apiService.getForecast(zipCode)
    }
}