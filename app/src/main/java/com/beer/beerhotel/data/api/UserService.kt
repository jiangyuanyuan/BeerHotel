package com.beer.beerhotel.data.api

import com.beer.baselibrary.data.dto.Rep
import com.beer.beerhotel.data.dto.UserInfo
import io.reactivex.Single
import retrofit2.http.*

interface UserService {

    /**
     * 获取验证码
     */
    @POST("kinship-api/getValidateCode")
    fun getValidateCode(): Single<Rep<String>>

//    /**
//     * 注册
//     */
//    @POST("kinship-api/register")
//    fun register(@Body registerReq: RegisterReq): Single<Rep<Register>>

//    /**
//     * 登录
//     */
//    @POST("kinship-api/login")
//    fun login(@Body loginReq: LoginReq): Single<Rep<Tokens>>

    /**
     * 获取用户信息
     */
    @GET("kinship-api/getLoginUser")
    fun getLoginUser(): Single<Rep<UserInfo>>

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