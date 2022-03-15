package com.tekfoods.features.weather.api

import com.tekfoods.features.task.api.TaskApi
import com.tekfoods.features.task.api.TaskRepo

object WeatherRepoProvider {
    fun weatherRepoProvider(): WeatherRepo {
        return WeatherRepo(WeatherApi.create())
    }
}