package com.ope.xchufa.data.dto

data class VerificationCode(
    val encryptAccount: String,
    val encryptPwd: String,
    val smsVerifyCode: String
)