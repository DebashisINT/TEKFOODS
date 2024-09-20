package com.tekfoodsbreezefield.features.damageProduct.api

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import com.tekfoodsbreezefield.app.FileUtils
import com.tekfoodsbreezefield.base.BaseResponse
import com.tekfoodsbreezefield.features.NewQuotation.model.*
import com.tekfoodsbreezefield.features.addshop.model.AddShopRequestData
import com.tekfoodsbreezefield.features.addshop.model.AddShopResponse
import com.tekfoodsbreezefield.features.damageProduct.model.DamageProductResponseModel
import com.tekfoodsbreezefield.features.damageProduct.model.delBreakageReq
import com.tekfoodsbreezefield.features.damageProduct.model.viewAllBreakageReq
import com.tekfoodsbreezefield.features.login.model.userconfig.UserConfigResponseModel
import com.tekfoodsbreezefield.features.myjobs.model.WIPImageSubmit
import com.tekfoodsbreezefield.features.photoReg.model.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class GetDamageProductListRegRepository(val apiService : GetDamageProductListApi) {

    fun viewBreakage(req: viewAllBreakageReq): Observable<DamageProductResponseModel> {
        return apiService.viewBreakage(req)
    }

    fun delBreakage(req: delBreakageReq): Observable<BaseResponse>{
        return apiService.BreakageDel(req.user_id!!,req.breakage_number!!,req.session_token!!)
    }

}