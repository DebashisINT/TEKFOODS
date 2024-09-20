package com.tekfoodsbreezefield.features.location.api

import com.tekfoodsbreezefield.features.location.shopdurationapi.ShopDurationApi
import com.tekfoodsbreezefield.features.location.shopdurationapi.ShopDurationRepository


object LocationRepoProvider {
    fun provideLocationRepository(): LocationRepo {
        return LocationRepo(LocationApi.create())
    }
}