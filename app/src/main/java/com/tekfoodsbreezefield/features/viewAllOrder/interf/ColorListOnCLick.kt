package com.tekfoodsbreezefield.features.viewAllOrder.interf

import com.tekfoodsbreezefield.app.domain.NewOrderGenderEntity
import com.tekfoodsbreezefield.features.viewAllOrder.model.ProductOrder

interface ColorListOnCLick {
    fun colorListOnCLick(size_qty_list: ArrayList<ProductOrder>, adpPosition:Int)
}