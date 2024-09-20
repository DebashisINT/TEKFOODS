package com.tekfoodsbreezefield.features.mylearning.apiCall

import com.tekfoodsbreezefield.features.login.api.opportunity.OpportunityListApi
import com.tekfoodsbreezefield.features.login.api.opportunity.OpportunityListRepo

object LMSRepoProvider {
    fun getTopicList(): LMSRepo {
        return LMSRepo(LMSApi.create())
    }
}