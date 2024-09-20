package com.tekfoodsbreezefield.features.photoReg.adapter

import com.tekfoodsbreezefield.features.photoReg.model.UserListResponseModel

interface PhotoAttendanceListner {
    fun getUserInfoOnLick(obj: UserListResponseModel)
    fun getUserInfoAttendReportOnLick(obj: UserListResponseModel)
}