package com.tekfoodsbreezefield.features.lead.api

import com.tekfoodsbreezefield.features.NewQuotation.api.GetQuotListRegRepository
import com.tekfoodsbreezefield.features.NewQuotation.api.GetQutoListApi


object GetLeadRegProvider {
    fun provideList(): GetLeadListRegRepository {
        return GetLeadListRegRepository(GetLeadListApi.create())
    }
}