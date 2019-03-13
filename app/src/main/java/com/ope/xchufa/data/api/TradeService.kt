package com.ope.xchufa.data.api

import com.ope.base.data.dto.Rep
import com.ope.base.data.dto.Req
import com.ope.xchufa.data.dto.*
import io.reactivex.Single
import retrofit2.http.*

interface TradeService {


    /**
     * 获取资产列表
     */
    @POST("/v1/asset/getList")
    fun getAssetsList(@Body req: Req): Single<Rep<List<Assets>>>


    /**
     * 获取广告列表
     */
    @POST("/v1/otc/adOrderList/fullInfo/get")
    fun getAdOrderList(@Body req: Req): Single<Rep<AD>>

    /**
     * 买
     */
    @POST("/v1/otc/add/buy/order")
    fun order(@Body req: Req): Single<Rep<Order>>
}