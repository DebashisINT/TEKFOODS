package com.tekfoodsbreezefield.features.login.model.opportunitymodel

import com.tekfoodsbreezefield.app.domain.OpportunityStatusEntity
import com.tekfoodsbreezefield.app.domain.ProductListEntity
import com.tekfoodsbreezefield.base.BaseResponse

/**
 * Created by Puja on 30.05.2024
 */
class OpportunityStatusListResponseModel : BaseResponse() {
    var status_list: ArrayList<OpportunityStatusEntity>? = null
}