package com.tekfoodsbreezefield.features.dashboard.presentation.api.dayStartEnd

import com.tekfoodsbreezefield.features.stockCompetetorStock.api.AddCompStockApi
import com.tekfoodsbreezefield.features.stockCompetetorStock.api.AddCompStockRepository

object DayStartEndRepoProvider {
    fun dayStartRepositiry(): DayStartEndRepository {
        return DayStartEndRepository(DayStartEndApi.create())
    }

}