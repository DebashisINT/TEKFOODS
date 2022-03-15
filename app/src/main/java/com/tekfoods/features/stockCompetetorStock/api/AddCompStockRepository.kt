package com.tekfoods.features.stockCompetetorStock.api

import com.tekfoods.base.BaseResponse
import com.tekfoods.features.orderList.model.NewOrderListResponseModel
import com.tekfoods.features.stockCompetetorStock.ShopAddCompetetorStockRequest
import com.tekfoods.features.stockCompetetorStock.model.CompetetorStockGetData
import io.reactivex.Observable

class AddCompStockRepository(val apiService:AddCompStockApi){

    fun addCompStock(shopAddCompetetorStockRequest: ShopAddCompetetorStockRequest): Observable<BaseResponse> {
        return apiService.submShopCompStock(shopAddCompetetorStockRequest)
    }

    fun getCompStockList(sessiontoken: String, user_id: String, date: String): Observable<CompetetorStockGetData> {
        return apiService.getCompStockList(sessiontoken, user_id, date)
    }
}