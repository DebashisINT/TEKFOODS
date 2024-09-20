package com.tekfoodsbreezefield.features.newcollectionreport

import com.tekfoodsbreezefield.features.photoReg.model.UserListResponseModel

interface PendingCollListner {
    fun getUserInfoOnLick(obj: PendingCollData)
}