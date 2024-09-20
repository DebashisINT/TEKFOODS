package com.tekfoodsbreezefield.features.viewAllOrder.interf

import com.tekfoodsbreezefield.app.domain.NewOrderGenderEntity
import com.tekfoodsbreezefield.features.viewAllOrder.model.ProductOrder
import java.text.FieldPosition

interface NewOrderSizeQtyDelOnClick {
    fun sizeQtySelListOnClick(product_size_qty: ArrayList<ProductOrder>)
    fun sizeQtyListOnClick(product_size_qty: ProductOrder,position: Int)
}