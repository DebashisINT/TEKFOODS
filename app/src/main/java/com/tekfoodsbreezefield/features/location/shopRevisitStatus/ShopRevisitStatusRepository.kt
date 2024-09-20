package com.tekfoodsbreezefield.features.location.shopRevisitStatus

import com.tekfoodsbreezefield.base.BaseResponse
import com.tekfoodsbreezefield.features.location.model.ShopDurationRequest
import com.tekfoodsbreezefield.features.location.model.ShopRevisitStatusRequest
import io.reactivex.Observable

class ShopRevisitStatusRepository(val apiService : ShopRevisitStatusApi) {
    fun shopRevisitStatus(shopRevisitStatus: ShopRevisitStatusRequest?): Observable<BaseResponse> {
        return apiService.submShopRevisitStatus(shopRevisitStatus)
    }
}