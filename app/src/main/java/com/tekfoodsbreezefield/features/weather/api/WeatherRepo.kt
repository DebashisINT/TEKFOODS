package com.tekfoodsbreezefield.features.weather.api

import com.tekfoodsbreezefield.base.BaseResponse
import com.tekfoodsbreezefield.features.task.api.TaskApi
import com.tekfoodsbreezefield.features.task.model.AddTaskInputModel
import com.tekfoodsbreezefield.features.weather.model.ForeCastAPIResponse
import com.tekfoodsbreezefield.features.weather.model.WeatherAPIResponse
import io.reactivex.Observable

class WeatherRepo(val apiService: WeatherApi) {
    fun getCurrentWeather(zipCode: String): Observable<WeatherAPIResponse> {
        return apiService.getTodayWeather(zipCode)
    }

    fun getWeatherForecast(zipCode: String): Observable<ForeCastAPIResponse> {
        return apiService.getForecast(zipCode)
    }
}