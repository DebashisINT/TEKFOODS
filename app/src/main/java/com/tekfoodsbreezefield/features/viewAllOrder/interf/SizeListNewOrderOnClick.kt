package com.tekfoodsbreezefield.features.viewAllOrder.interf

import com.tekfoodsbreezefield.app.domain.NewOrderProductEntity
import com.tekfoodsbreezefield.app.domain.NewOrderSizeEntity

interface SizeListNewOrderOnClick {
    fun sizeListOnClick(size: NewOrderSizeEntity)
}