package com.ope.xchufa.data.dto

/**
 * 用户信息实体类
 */


data class VerificationCode(
        val encryptAccount: String,
        val encryptPwd: String,
        val smsVerifyCode: String
)

//data class Account (
//        val email: String,
//        val googleAuthenticator: Int,
//        val headImg: Int,
//        val idNumber: Int,
//        val isHasTxPassword: Int,
//        val level: Int,
//        val mobile: String,
//        val name: Int,
//        val nationalityCode: Int,
//        val nickName: Int,
//        val salerLevel: Int,
//        val shortPhone: String,
//        val status: Int,
//        val userId: Int,
//        val userName: String
//)
//data class UserInfo(
//        val account: Account,
//        val token:String
//)
data class UserInfo(
    val account: Account,
    val token: String
)

data class Account(
    val birthday: String,
    val email: String,
    val googleAuthenticator: Int,
    val headImg: String,
    val idNumber: String,
    val isHasTxPassword: Int,
    val level: Int,
    val mobile: String,
    val name: String,
    val nationalityCode: String,
    val nickName: String,
    val salerLevel: Int,
    val salerLogined: String,
    val shortPhone: String,
    val status: Int,
    val userId: Int,
    val userName: String
)