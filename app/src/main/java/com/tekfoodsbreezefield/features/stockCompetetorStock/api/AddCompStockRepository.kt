package com.tekfoodsbreezefield.features.stockCompetetorStock.api

import com.tekfoodsbreezefield.base.BaseResponse
import com.tekfoodsbreezefield.features.orderList.model.NewOrderListResponseModel
import com.tekfoodsbreezefield.features.stockCompetetorStock.ShopAddCompetetorStockRequest
import com.tekfoodsbreezefield.features.stockCompetetorStock.model.CompetetorStockGetData
import io.reactivex.Observable

class AddCompStockRepository(val apiService:AddCompStockApi){

    fun addCompStock(shopAddCompetetorStockRequest: ShopAddCompetetorStockRequest): Observable<BaseResponse> {
        return apiService.submShopCompStock(shopAddCompetetorStockRequest)
    }

    fun getCompStockList(sessiontoken: String, user_id: String, date: String): Observable<CompetetorStockGetData> {
        return apiService.getCompStockList(sessiontoken, user_id, date)
    }
}