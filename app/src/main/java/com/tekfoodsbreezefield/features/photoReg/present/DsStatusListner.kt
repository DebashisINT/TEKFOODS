package com.tekfoodsbreezefield.features.photoReg.present

import com.tekfoodsbreezefield.app.domain.ProspectEntity
import com.tekfoodsbreezefield.features.photoReg.model.UserListResponseModel

interface DsStatusListner {
    fun getDSInfoOnLick(obj: ProspectEntity)
}