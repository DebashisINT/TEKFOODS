package com.tekfoods.features.newcollection.model

import com.tekfoods.app.domain.CollectionDetailsEntity
import com.tekfoods.base.BaseResponse
import com.tekfoods.features.shopdetail.presentation.model.collectionlist.CollectionListDataModel

/**
 * Created by Saikat on 15-02-2019.
 */
class NewCollectionListResponseModel : BaseResponse() {
    //var collection_list: ArrayList<CollectionListDataModel>? = null
    var collection_list: ArrayList<CollectionDetailsEntity>? = null
}