package com.tekfoodsbreezefield.features.stockAddCurrentStock.api

import com.tekfoodsbreezefield.features.location.shopRevisitStatus.ShopRevisitStatusApi
import com.tekfoodsbreezefield.features.location.shopRevisitStatus.ShopRevisitStatusRepository

object ShopAddStockProvider {
    fun provideShopAddStockRepository(): ShopAddStockRepository {
        return ShopAddStockRepository(ShopAddStockApi.create())
    }
}