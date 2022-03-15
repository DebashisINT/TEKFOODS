package com.tekfoods.app.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tekfoods.app.AppConstant

/**
 * Created by Saikat on 23-Sep-18.
 */
@Dao
interface OrderDetailsListDao {

    @Query("SELECT * FROM " + AppConstant.ORDER_DETAILS_LIST_TABLE + " order by id desc")
    fun getAll(): List<OrderDetailsListEntity>

    @Query("SELECT * FROM " + AppConstant.ORDER_DETAILS_LIST_TABLE + " where shop_id=:shop_id order by id desc")
    fun getListAccordingToShopId(shop_id: String): List<OrderDetailsListEntity>



    @Query("SELECT * FROM " + AppConstant.ORDER_DETAILS_LIST_TABLE + " where isUploaded=:isUploaded and order_id=:order_id")
    fun getUnsyncListAccordingToOrderId(order_id: String, isUploaded: Boolean): List<OrderDetailsListEntity>

    @Query("SELECT * FROM " + AppConstant.ORDER_DETAILS_LIST_TABLE + " where isUploaded=:isUploaded")
    fun getUnsyncedData(isUploaded: Boolean): List<OrderDetailsListEntity>

    @Query("SELECT MAX(CAST(amount as DOUBLE)) FROM " + AppConstant.ORDER_DETAILS_LIST_TABLE + " where shop_id=:shop_id")
    fun getAmountAccordingToShopId(shop_id: String): String

    @Insert
    fun insert(vararg orderDetails: OrderDetailsListEntity)

    @Query("update " + AppConstant.ORDER_DETAILS_LIST_TABLE + " set isUploaded=:isUploaded where order_id=:order_id")
    fun updateIsUploaded(isUploaded: Boolean, order_id: String)

    @Query("SELECT * FROM " + AppConstant.ORDER_DETAILS_LIST_TABLE + " where only_date=:date order by id desc")
    fun getListAccordingDate(date: String): List<OrderDetailsListEntity>

    @Query("DELETE FROM " + AppConstant.ORDER_DETAILS_LIST_TABLE)
    fun delete()

    @Query("SELECT * FROM " + AppConstant.ORDER_DETAILS_LIST_TABLE + " where order_id=:order_id")
    fun getSingleOrder(order_id: String): OrderDetailsListEntity
}