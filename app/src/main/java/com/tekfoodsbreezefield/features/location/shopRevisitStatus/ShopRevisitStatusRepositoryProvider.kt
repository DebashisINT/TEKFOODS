package com.tekfoodsbreezefield.features.location.shopRevisitStatus

import com.tekfoodsbreezefield.features.location.shopdurationapi.ShopDurationApi
import com.tekfoodsbreezefield.features.location.shopdurationapi.ShopDurationRepository

object ShopRevisitStatusRepositoryProvider {
    fun provideShopRevisitStatusRepository(): ShopRevisitStatusRepository {
        return ShopRevisitStatusRepository(ShopRevisitStatusApi.create())
    }
}