package com.ope.xchufa.data.api

import com.ope.base.data.dto.Rep
import com.ope.base.data.dto.Req
import com.ope.xchufa.data.dto.UserInfo
import com.ope.xchufa.data.dto.VerificationCode
import io.reactivex.Single
import retrofit2.http.*

interface UserService {


    /**
     * 获取验证码
     */

    @POST("/v1/sms/verifycode")
    fun getVerificationCode(@Body req: HashMap<String,String>): Single<Rep<VerificationCode>>

    /**
     * 登录
     */
    @POST("/v1/account/login")
    fun login(@Body req: Req): Single<Rep<UserInfo>>

    /**
     * 注册
     */
    @POST("/v1/account/register")
    fun rigster(@Body req: Req): Single<Rep<UserInfo>>


    /**
     * 登出
     */
    @POST("/v1/account/logout")
    fun logout(@Body req: Req): Single<Rep<Int>>



    /**
     * 修改密码
     */
    @POST("/v1/account/resetPassword")
    fun resetPwd(@Body req: Req): Single<Rep<Int>>




//    /**
//     * 验证token是否有效
//     */
//    @POST("kinship-api/verifyToken")
//    fun verifyToken(@Body verifyReq: VerifyReq): Single<Rep<VerifyToken>>
//
//    /**
//     * 刷新TOKEN
//     */
//    @POST("kinship-api/refreshToken")
//    fun refreshToken(): Single<Rep<Tokens>>
//
//    /**
//     * 登出
//     */
//    @POST("kinship-api/logout")
//    fun logout(): Single<Rep<Any>>
//
//    /**
//     * 修改用户信息
//     */
//    @PUT("kinship-api/user")
//    fun updateUserInfo(@Body userInfo: UserInfo): Single<Rep<UserInfo>>
//
//    /**
//     * 获取快捷登录的短信验证码
//     */
//    @POST("kinship-api/smsLoginValidateCode")
//    fun getSmsCode(@Body smsCodeReq: SmsCodeReq): Single<Rep<String>>
//
//    /**
//     * 获取注册的短信验证码
//     */
//    @POST("kinship-api/smsGetValidateCode")
//    fun getRegisterSmsCode(@Body smsCodeReq: SmsCodeReq): Single<Rep<String>>
//
//    /**
//     * 获取重置密码的短信验证码
//     */
//    @POST("kinship-api/smsResetValidateCode")
//    fun getResetSmsCode(@Body smsCodeReq: SmsCodeReq): Single<Rep<String>>
//
//    /**
//     * 快捷登录
//     */
//    @POST("kinship-api/quickLogin")
//    fun quickLogin(@Body quickLoginReq: QuickLoginReq): Single<Rep<Tokens>>
//
//    /**
//     * 重置密码
//     */
//    @POST("kinship-api/reset")
//    fun resetPwd(@Body resetPwdReq: ResetPwdReq): Single<Rep<String>>

}