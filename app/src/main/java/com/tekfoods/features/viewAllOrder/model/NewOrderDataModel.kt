package com.tekfoods.features.viewAllOrder.model

import com.tekfoods.app.domain.NewOrderColorEntity
import com.tekfoods.app.domain.NewOrderGenderEntity
import com.tekfoods.app.domain.NewOrderProductEntity
import com.tekfoods.app.domain.NewOrderSizeEntity
import com.tekfoods.features.stockCompetetorStock.model.CompetetorStockGetDataDtls

class NewOrderDataModel {
    var status:String ? = null
    var message:String ? = null
    var Gender_list :ArrayList<NewOrderGenderEntity>? = null
    var Product_list :ArrayList<NewOrderProductEntity>? = null
    var Color_list :ArrayList<NewOrderColorEntity>? = null
    var size_list :ArrayList<NewOrderSizeEntity>? = null
}

