package com.tekfoods.features.nearbyuserlist.api

import com.tekfoods.app.Pref
import com.tekfoods.features.nearbyuserlist.model.NearbyUserResponseModel
import com.tekfoods.features.newcollection.model.NewCollectionListResponseModel
import com.tekfoods.features.newcollection.newcollectionlistapi.NewCollectionListApi
import io.reactivex.Observable

class NearbyUserRepo(val apiService: NearbyUserApi) {
    fun nearbyUserList(): Observable<NearbyUserResponseModel> {
        return apiService.getNearbyUserList(Pref.session_token!!, Pref.user_id!!)
    }
}