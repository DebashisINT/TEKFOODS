package com.tekfoods.features.location.shopRevisitStatus

import com.tekfoods.base.BaseResponse
import com.tekfoods.features.location.model.ShopDurationRequest
import com.tekfoods.features.location.model.ShopRevisitStatusRequest
import io.reactivex.Observable

class ShopRevisitStatusRepository(val apiService : ShopRevisitStatusApi) {
    fun shopRevisitStatus(shopRevisitStatus: ShopRevisitStatusRequest?): Observable<BaseResponse> {
        return apiService.submShopRevisitStatus(shopRevisitStatus)
    }
}