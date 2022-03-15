package com.tekfoods.features.dashboard.presentation.api.dayStartEnd

import com.tekfoods.features.stockCompetetorStock.api.AddCompStockApi
import com.tekfoods.features.stockCompetetorStock.api.AddCompStockRepository

object DayStartEndRepoProvider {
    fun dayStartRepositiry(): DayStartEndRepository {
        return DayStartEndRepository(DayStartEndApi.create())
    }

}