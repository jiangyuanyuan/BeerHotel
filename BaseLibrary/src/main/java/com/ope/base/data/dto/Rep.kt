package com.ope.base.data.dto

data class Rep<T>(val message: String,
                  val code: String,
                  val data: T) {

    fun isSuccessful() = code == RepCode.COMMON_SUC_CODE.code
}

enum class RepCode(val code: String, val message: String) {
    COMMON_SUC_CODE("000000", "ok"),
    COMMON_ERR_CODE("999999", "server error"),
    COMMON_SMSVC_ERR_CODE("999998", "server error"),

    COMMOM_PARAMS_ERR_CODE("999995", "params error"),
    COMMOM_INEXISTENCE_ERR_CODE("999994", "inexistence"),

    TOKEN_EXPIRED("999991", "token expired"),
    ADDRESS_GEN_ERR("999990", "address generate error"),


    ACCOUNT_EXISTENCE_ERR_CODE("009993", "account existence"),
    ACCOUNT_UNEXISTENCE_ERR_CODE("009993", "account unexistence"),
    ACCOUNT_DECRYT_ERROR("009992", "decryt error"),
    ACCOUNT_REPEAT_ERR_CODE("009997", "account repeat"),
    ACCOUNT_LOGIN_ERR_CODE("009996", "login error"),
    ACCOUNT_USERNAME_HAS_BEEN_USE("009989", "username existence"),

    MESSAGE_SMS_SEND_ERR_CODE("019993", "sms send fail"),

    OTC_ORDER_CAN_NOT_CONTROLL_BY_YOU("029990", "you can't not controll the order");

}













