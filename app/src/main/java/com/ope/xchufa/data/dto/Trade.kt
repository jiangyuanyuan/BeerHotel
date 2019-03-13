package com.ope.xchufa.data.dto

import java.math.BigDecimal


data class Assets(
    val address: String,
    val assetId: Int,
    val code: String,
    val coinPrecision: Int,
    val createTime: String,
    val desc: String,
    val explorer: String,
    val icon: String,
    val nameCn: String,
    val nameEn: String,
    val parentId: String,
    val status: Int,
    val updatedTime: String
)






data class SaleIntro(
        val email: String,
        val headImg: String,
        val level: Int,
        val mobile: String,
        val name: String,
        val nickName: String,
        val shortPhone: String,
        val status: Int,
        val userId: Int,
        val userName: String
)


data class Sort(
    val sorted: Boolean,
    val unsorted: Boolean
)



data class AD(
        val content: List<Content>,
        val first: Boolean,
        val last: Boolean,
        val number: Int,
        val numberOfElements: Int,
        val pageable: Pageable,
        val size: Int,
        val sort: Sort,
        val totalElements: Int,
        val totalPages: Int
)


data class Pageable(
    val offset: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val paged: Boolean,
    val sort: Sort,
    val unpaged: Boolean
)



data class Content(
        val adOrder: AdOrder,
        val payInfos: List<PayInfo>,
        val saleIntro: SaleIntro
)


data class PayInfo(
        val aliPayAccount: String,
        val aliPayQrcode: String,
        val cardId: String,
        val cardName: String,
        val createTime: Long,
        val name: String,
        val payInfoId: Int,
        val payType: Int,
        val updateTime: Long,
        val userId: Int,
        val wechatPayAccount: String,
        val wechatPayQrcode: String
)


data class AdOrder(
        val adOrderId: Int,
        val assetId: Int,
        val createTime: Long,
        val currencyCode: String,
        val endTime: Any,
        val feeType: Int,
        val leftAmount: BigDecimal,
        val maxTxAmount: BigDecimal,
        val minTxAmount: BigDecimal,
        val orderKind: Int,
        val orderType: Int,
        val price: BigDecimal,
        val remark: Any,
        val status: Int,
        val supportPayway: String,
        val totalAmount: BigDecimal,
        val updateTime: Long,
        val userId: Int
)


data class Order(
    val adOrderId: Int,
    val amount: BigDecimal,
    val assetId: Int,
    val cacelRemark: String,
    val createTime: Long,
    val endTime: Long,
    val feeType: Int,
    val leftAmount: BigDecimal,
    val maxTxAmount: BigDecimal,
    val minTxAmount: BigDecimal,
    val orderKind: Int,
    val orderType: Int,
    val otcOrderId: Int,
    val payInfoId: Long,
    val payway: Int,
    val price: BigDecimal,
    val randCode: String,
    val rateFee: BigDecimal,
    val remark: String,
    val status: Int,
    val supportPayIds: String,
    val systemRemark: String,
    val timeLimit: Int,
    val toUserId: Int,
    val totalAmount: BigDecimal,
    val updateTime: Long,
    val userId: Int,
    val volume: BigDecimal
)