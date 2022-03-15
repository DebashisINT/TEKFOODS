package com.tekfoods.features.viewAllOrder.interf

import com.tekfoods.app.domain.NewOrderProductEntity
import com.tekfoods.app.domain.NewOrderSizeEntity

interface SizeListNewOrderOnClick {
    fun sizeListOnClick(size: NewOrderSizeEntity)
}