package com.tekfoodsbreezefield.features.document.api

import com.tekfoodsbreezefield.features.dymanicSection.api.DynamicApi
import com.tekfoodsbreezefield.features.dymanicSection.api.DynamicRepo

object DocumentRepoProvider {
    fun documentRepoProvider(): DocumentRepo {
        return DocumentRepo(DocumentApi.create())
    }

    fun documentRepoProviderMultipart(): DocumentRepo {
        return DocumentRepo(DocumentApi.createImage())
    }
}