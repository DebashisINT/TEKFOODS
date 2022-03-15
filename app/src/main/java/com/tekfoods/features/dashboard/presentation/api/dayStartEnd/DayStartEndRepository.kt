package com.tekfoods.features.dashboard.presentation.api.dayStartEnd

import com.tekfoods.app.Pref
import com.tekfoods.base.BaseResponse
import com.tekfoods.features.dashboard.presentation.model.DaystartDayendRequest
import com.tekfoods.features.dashboard.presentation.model.StatusDayStartEnd
import com.tekfoods.features.stockCompetetorStock.ShopAddCompetetorStockRequest
import com.tekfoods.features.stockCompetetorStock.api.AddCompStockApi
import io.reactivex.Observable

class DayStartEndRepository (val apiService: DayStartEndApi){
    fun dayStart(daystartDayendRequest: DaystartDayendRequest): Observable<BaseResponse> {
        return apiService.submitDayStartEnd(daystartDayendRequest)
    }

    fun dayStartEndStatus(date:String): Observable<StatusDayStartEnd> {
        return apiService.statusDayStartEnd(Pref.session_token!!, Pref.user_id!!,date)
    }


}