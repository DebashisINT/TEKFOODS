package com.tekfoods.features.stockAddCurrentStock.api

import com.tekfoods.base.BaseResponse
import com.tekfoods.features.location.model.ShopRevisitStatusRequest
import com.tekfoods.features.location.shopRevisitStatus.ShopRevisitStatusApi
import com.tekfoods.features.stockAddCurrentStock.ShopAddCurrentStockRequest
import com.tekfoods.features.stockAddCurrentStock.model.CurrentStockGetData
import com.tekfoods.features.stockCompetetorStock.model.CompetetorStockGetData
import io.reactivex.Observable

class ShopAddStockRepository (val apiService : ShopAddStockApi){
    fun shopAddStock(shopAddCurrentStockRequest: ShopAddCurrentStockRequest?): Observable<BaseResponse> {
        return apiService.submShopAddStock(shopAddCurrentStockRequest)
    }

    fun getCurrStockList(sessiontoken: String, user_id: String, date: String): Observable<CurrentStockGetData> {
        return apiService.getCurrStockListApi(sessiontoken, user_id, date)
    }

}