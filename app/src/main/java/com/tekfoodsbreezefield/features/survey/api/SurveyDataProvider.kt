package com.tekfoodsbreezefield.features.survey.api

import com.tekfoodsbreezefield.features.photoReg.api.GetUserListPhotoRegApi
import com.tekfoodsbreezefield.features.photoReg.api.GetUserListPhotoRegRepository

object SurveyDataProvider{

    fun provideSurveyQ(): SurveyDataRepository {
        return SurveyDataRepository(SurveyDataApi.create())
    }

    fun provideSurveyQMultiP(): SurveyDataRepository {
        return SurveyDataRepository(SurveyDataApi.createImage())
    }
}