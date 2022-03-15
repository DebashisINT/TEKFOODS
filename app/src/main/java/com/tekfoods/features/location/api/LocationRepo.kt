package com.tekfoods.features.location.api

import com.tekfoods.app.Pref
import com.tekfoods.base.BaseResponse
import com.tekfoods.features.location.model.AppInfoInputModel
import com.tekfoods.features.location.model.AppInfoResponseModel
import com.tekfoods.features.location.model.ShopDurationRequest
import com.tekfoods.features.location.shopdurationapi.ShopDurationApi
import io.reactivex.Observable

/**
 * Created by Saikat on 17-Aug-20.
 */
class LocationRepo(val apiService: LocationApi) {
    fun appInfo(appInfo: AppInfoInputModel?): Observable<BaseResponse> {
        return apiService.submitAppInfo(appInfo)
    }

    fun getAppInfo(): Observable<AppInfoResponseModel> {
        return apiService.getAppInfo(Pref.session_token!!, Pref.user_id!!)
    }
}