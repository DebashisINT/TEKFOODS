package com.tekfoodsbreezefield.features.nearbyuserlist.api

import com.tekfoodsbreezefield.app.Pref
import com.tekfoodsbreezefield.features.nearbyuserlist.model.NearbyUserResponseModel
import com.tekfoodsbreezefield.features.newcollection.model.NewCollectionListResponseModel
import com.tekfoodsbreezefield.features.newcollection.newcollectionlistapi.NewCollectionListApi
import io.reactivex.Observable

class NearbyUserRepo(val apiService: NearbyUserApi) {
    fun nearbyUserList(): Observable<NearbyUserResponseModel> {
        return apiService.getNearbyUserList(Pref.session_token!!, Pref.user_id!!)
    }
}