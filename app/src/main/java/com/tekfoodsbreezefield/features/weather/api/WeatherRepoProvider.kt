package com.tekfoodsbreezefield.features.weather.api

import com.tekfoodsbreezefield.features.task.api.TaskApi
import com.tekfoodsbreezefield.features.task.api.TaskRepo

object WeatherRepoProvider {
    fun weatherRepoProvider(): WeatherRepo {
        return WeatherRepo(WeatherApi.create())
    }
}