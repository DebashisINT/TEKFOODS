package com.tekfoodsbreezefield.features.dashboard.presentation.api.unreadnotificationapi

import com.tekfoodsbreezefield.app.Pref
import com.tekfoodsbreezefield.features.dashboard.presentation.model.UnreadNotificationResponseModel
import io.reactivex.Observable

/**
 * Created by Saikat on 07-03-2019.
 */
class UnreadNotificationRepo(val apiService: UnreadNotificationApi) {
    fun unreadNotification(): Observable<UnreadNotificationResponseModel> {
        return apiService.unreadNotification(Pref.session_token!!, Pref.user_id!!)
    }
}