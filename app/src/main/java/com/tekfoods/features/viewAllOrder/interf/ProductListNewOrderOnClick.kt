package com.tekfoods.features.viewAllOrder.interf

import com.tekfoods.app.domain.NewOrderGenderEntity
import com.tekfoods.app.domain.NewOrderProductEntity

interface ProductListNewOrderOnClick {
    fun productListOnClick(product: NewOrderProductEntity)
}