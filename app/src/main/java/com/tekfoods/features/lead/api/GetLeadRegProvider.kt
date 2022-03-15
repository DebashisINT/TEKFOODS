package com.tekfoods.features.lead.api

import com.tekfoods.features.NewQuotation.api.GetQuotListRegRepository
import com.tekfoods.features.NewQuotation.api.GetQutoListApi


object GetLeadRegProvider {
    fun provideList(): GetLeadListRegRepository {
        return GetLeadListRegRepository(GetLeadListApi.create())
    }
}