package com.tekfoodsbreezefield.features.login.model.productlistmodel

import com.tekfoodsbreezefield.app.domain.ModelEntity
import com.tekfoodsbreezefield.app.domain.ProductListEntity
import com.tekfoodsbreezefield.base.BaseResponse

class ModelListResponse: BaseResponse() {
    var model_list: ArrayList<ModelEntity>? = null
}