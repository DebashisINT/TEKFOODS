package com.tekfoodsbreezefield.features.activities.api

import com.tekfoodsbreezefield.features.member.api.TeamApi
import com.tekfoodsbreezefield.features.member.api.TeamRepo

object ActivityRepoProvider {
    fun activityRepoProvider(): ActivityRepo {
        return ActivityRepo(ActivityApi.create())
    }

    fun activityImageRepoProvider(): ActivityRepo {
        return ActivityRepo(ActivityApi.createImage())
    }
}