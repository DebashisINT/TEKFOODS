package com.tekfoodsbreezefield.features.viewAllOrder.interf

import com.tekfoodsbreezefield.app.domain.NewOrderColorEntity
import com.tekfoodsbreezefield.app.domain.NewOrderProductEntity

interface ColorListNewOrderOnClick {
    fun productListOnClick(color: NewOrderColorEntity)
}