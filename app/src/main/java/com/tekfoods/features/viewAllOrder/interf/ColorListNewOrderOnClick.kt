package com.tekfoods.features.viewAllOrder.interf

import com.tekfoods.app.domain.NewOrderColorEntity
import com.tekfoods.app.domain.NewOrderProductEntity

interface ColorListNewOrderOnClick {
    fun productListOnClick(color: NewOrderColorEntity)
}