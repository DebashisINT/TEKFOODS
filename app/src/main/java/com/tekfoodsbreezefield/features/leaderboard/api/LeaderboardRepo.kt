package com.tekfoodsbreezefield.features.leaderboard.api

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import com.fasterxml.jackson.databind.ObjectMapper
import com.tekfoodsbreezefield.app.FileUtils
import com.tekfoodsbreezefield.app.Pref
import com.tekfoodsbreezefield.base.BaseResponse
import com.tekfoodsbreezefield.features.addshop.model.AddLogReqData
import com.tekfoodsbreezefield.features.addshop.model.AddShopRequestData
import com.tekfoodsbreezefield.features.addshop.model.AddShopResponse
import com.tekfoodsbreezefield.features.addshop.model.LogFileResponse
import com.tekfoodsbreezefield.features.addshop.model.UpdateAddrReq
import com.tekfoodsbreezefield.features.contacts.CallHisDtls
import com.tekfoodsbreezefield.features.contacts.CompanyReqData
import com.tekfoodsbreezefield.features.contacts.ContactMasterRes
import com.tekfoodsbreezefield.features.contacts.SourceMasterRes
import com.tekfoodsbreezefield.features.contacts.StageMasterRes
import com.tekfoodsbreezefield.features.contacts.StatusMasterRes
import com.tekfoodsbreezefield.features.contacts.TypeMasterRes
import com.tekfoodsbreezefield.features.dashboard.presentation.DashboardActivity
import com.tekfoodsbreezefield.features.login.model.WhatsappApiData
import com.tekfoodsbreezefield.features.login.model.WhatsappApiFetchData
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by Puja on 10-10-2024.
 */
class LeaderboardRepo(val apiService: LeaderboardApi) {

    fun branchlist(session_token: String): Observable<LeaderboardBranchData> {
        return apiService.branchList(session_token)
    }
    fun ownDatalist(user_id: String,activitybased: String,branchwise: String,flag: String): Observable<LeaderboardOwnData> {
        return apiService.ownDatalist(user_id,activitybased,branchwise,flag)
    }
    fun overAllAPI(user_id: String,activitybased: String,branchwise: String,flag: String): Observable<LeaderboardOverAllData> {
        return apiService.overAllDatalist(user_id,activitybased,branchwise,flag)
    }
}