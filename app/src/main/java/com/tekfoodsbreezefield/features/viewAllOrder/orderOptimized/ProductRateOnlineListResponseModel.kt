package com.tekfoodsbreezefield.features.viewAllOrder.orderOptimized

import com.tekfoodsbreezefield.app.domain.ProductOnlineRateTempEntity
import com.tekfoodsbreezefield.base.BaseResponse
import com.tekfoodsbreezefield.features.login.model.productlistmodel.ProductRateDataModel
import java.io.Serializable

class ProductRateOnlineListResponseModel: BaseResponse(), Serializable {
    var product_rate_list: ArrayList<ProductOnlineRateTempEntity>? = null
}