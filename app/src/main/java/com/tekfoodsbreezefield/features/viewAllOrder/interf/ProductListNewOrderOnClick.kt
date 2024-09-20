package com.tekfoodsbreezefield.features.viewAllOrder.interf

import com.tekfoodsbreezefield.app.domain.NewOrderGenderEntity
import com.tekfoodsbreezefield.app.domain.NewOrderProductEntity

interface ProductListNewOrderOnClick {
    fun productListOnClick(product: NewOrderProductEntity)
}