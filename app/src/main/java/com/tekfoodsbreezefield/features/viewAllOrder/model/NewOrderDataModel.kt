package com.tekfoodsbreezefield.features.viewAllOrder.model

import com.tekfoodsbreezefield.app.domain.NewOrderColorEntity
import com.tekfoodsbreezefield.app.domain.NewOrderGenderEntity
import com.tekfoodsbreezefield.app.domain.NewOrderProductEntity
import com.tekfoodsbreezefield.app.domain.NewOrderSizeEntity
import com.tekfoodsbreezefield.features.stockCompetetorStock.model.CompetetorStockGetDataDtls

class NewOrderDataModel {
    var status:String ? = null
    var message:String ? = null
    var Gender_list :ArrayList<NewOrderGenderEntity>? = null
    var Product_list :ArrayList<NewOrderProductEntity>? = null
    var Color_list :ArrayList<NewOrderColorEntity>? = null
    var size_list :ArrayList<NewOrderSizeEntity>? = null
}

