package com.tekfoodsbreezefield.features.reimbursement.api.editapi

import com.tekfoodsbreezefield.base.BaseResponse
import com.tekfoodsbreezefield.features.reimbursement.model.ApplyReimbursementInputModel
import io.reactivex.Observable

/**
 * Created by Saikat on 08-02-2019.
 */
class ReimbursementEditRepo(val apiService: ReimbursementEditApi) {
    fun editReimbursement(input: ApplyReimbursementInputModel): Observable<BaseResponse> {
        return apiService.editReimbursement(input)
    }
}