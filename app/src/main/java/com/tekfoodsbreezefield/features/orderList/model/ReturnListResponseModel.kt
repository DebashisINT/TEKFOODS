package com.tekfoodsbreezefield.features.orderList.model

import com.tekfoodsbreezefield.base.BaseResponse


class ReturnListResponseModel: BaseResponse() {
    var return_list: ArrayList<ReturnDataModel>? = null
}