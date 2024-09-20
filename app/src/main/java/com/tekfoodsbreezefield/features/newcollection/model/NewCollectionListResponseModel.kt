package com.tekfoodsbreezefield.features.newcollection.model

import com.tekfoodsbreezefield.app.domain.CollectionDetailsEntity
import com.tekfoodsbreezefield.base.BaseResponse
import com.tekfoodsbreezefield.features.shopdetail.presentation.model.collectionlist.CollectionListDataModel

/**
 * Created by Saikat on 15-02-2019.
 */
class NewCollectionListResponseModel : BaseResponse() {
    //var collection_list: ArrayList<CollectionListDataModel>? = null
    var collection_list: ArrayList<CollectionDetailsEntity>? = null
}