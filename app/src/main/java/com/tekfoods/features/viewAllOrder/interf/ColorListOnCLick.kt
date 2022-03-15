package com.tekfoods.features.viewAllOrder.interf

import com.tekfoods.app.domain.NewOrderGenderEntity
import com.tekfoods.features.viewAllOrder.model.ProductOrder

interface ColorListOnCLick {
    fun colorListOnCLick(size_qty_list: ArrayList<ProductOrder>, adpPosition:Int)
}