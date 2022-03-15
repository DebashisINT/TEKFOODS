package com.tekfoods.features.location.shopRevisitStatus

import com.tekfoods.features.location.shopdurationapi.ShopDurationApi
import com.tekfoods.features.location.shopdurationapi.ShopDurationRepository

object ShopRevisitStatusRepositoryProvider {
    fun provideShopRevisitStatusRepository(): ShopRevisitStatusRepository {
        return ShopRevisitStatusRepository(ShopRevisitStatusApi.create())
    }
}