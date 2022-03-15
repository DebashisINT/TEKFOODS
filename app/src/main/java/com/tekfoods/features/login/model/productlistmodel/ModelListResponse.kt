package com.tekfoods.features.login.model.productlistmodel

import com.tekfoods.app.domain.ModelEntity
import com.tekfoods.app.domain.ProductListEntity
import com.tekfoods.base.BaseResponse

class ModelListResponse: BaseResponse() {
    var model_list: ArrayList<ModelEntity>? = null
}