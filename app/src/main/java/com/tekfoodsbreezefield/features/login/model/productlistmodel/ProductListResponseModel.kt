package com.tekfoodsbreezefield.features.login.model.productlistmodel

import com.tekfoodsbreezefield.app.domain.ProductListEntity
import com.tekfoodsbreezefield.base.BaseResponse

/**
 * Created by Saikat on 20-11-2018.
 */
class ProductListResponseModel : BaseResponse() {
    //var product_list: ArrayList<ProductListDataModel>? = null
    var product_list: ArrayList<ProductListEntity>? = null
}