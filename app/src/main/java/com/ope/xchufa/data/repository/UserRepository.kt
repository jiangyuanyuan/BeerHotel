package com.ope.xchufa.data.repository

import com.ope.base.data.dto.Rep
import com.ope.base.data.dto.Req
import com.ope.base.helper.applySchedulersOnSingle
import com.ope.base.helper.loge
import com.ope.xchufa.data.api.UserService

import com.ope.xchufa.data.dto.UserInfo
import com.ope.xchufa.data.dto.VerificationCode
import com.ope.xchufa.ext.getToken
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(private val userService: UserService) {


    fun getVerificationCode(mobile: String, shortPhone: String): Single<Rep<VerificationCode>> {
        val req = HashMap<String,String>()
        req.put("mobile",mobile)
        req.put("shortPhone",shortPhone)
        return userService.getVerificationCode(req)
                .compose(applySchedulersOnSingle())
    }

    fun login(encryptAccount: String, encryptPwd: String,userName: String, userType: String, shortPhone: String): Single<Rep<UserInfo>> {
        val req = Req()
        req.putParams("encryptAccount",encryptAccount)
        req.putParams("encryptPwd",encryptPwd)
        req.putParams("userName",userName)
        req.putParams("userType",userType)
        req.putParams("shortPhone",shortPhone)
        return userService.login(req)
                .compose(applySchedulersOnSingle())
    }

    fun rigster(userName: String,encryptAccount: String, encryptPwd: String, userType: String, shortPhone: String): Single<Rep<UserInfo>> {
        val req = Req()
        req.putParams("encryptAccount",encryptAccount)
        req.putParams("encryptPwd",encryptPwd)
        req.putParams("userName",userName)
        req.putParams("userType",userType)
        req.putParams("shortPhone",shortPhone)
        return userService.rigster(req)
                .compose(applySchedulersOnSingle())
    }

    fun logout(): Single<Rep<Int>> {
        val req = Req()
        return userService.logout(req)
                .compose(applySchedulersOnSingle())
    }

    fun resetPwd(encryptAccount: String, encryptPwd: String, userType: String, shortPhone: String): Single<Rep<Int>> {
        val req = Req()
        req.putParams("encryptAccount",encryptAccount)
        req.putParams("encryptPwd",encryptPwd)
        req.putParams("userType",userType)
        req.putParams("shortPhone",shortPhone)
        return userService.resetPwd(req)
                .compose(applySchedulersOnSingle())
    }


//
//    fun getLoginUser(): Single<Rep<UserInfo>> {
//        return userService.getLoginUser()
//                .map {
//                    if (it.isSuccessful()){
//                        Hawk.put(KEY_USER_INFO,it.entry)
//                    }
//                    it
//                }
//                .compose(applySchedulersOnSingle())
//    }
//
//    fun verifyToken(token: String): Single<Rep<VerifyToken>> {
//        return userService.verifyToken(VerifyReq(token))
//                .compose(applySchedulersOnSingle())
//    }
//
//    fun refreshToken(): Single<Rep<Tokens>> {
//        return userService.refreshToken()
//                .map {
//                    if (it.isSuccessful()){
//                        Hawk.put(KEY_TOKEN, it.entry.login)
//                        Hawk.put(KEY_TOKEN_IM, it.entry.message)
//                    }
//                    it
//                }
//                .compose(applySchedulersOnSingle())
//    }
//
//
//    fun updateUserInfo(userInfo: UserInfo): Single<Rep<UserInfo>> {
//        return userService.updateUserInfo(userInfo)
//                .map {
//                    if (it.isSuccessful()){
//                        Hawk.put(KEY_USER_INFO,it.entry)
//                    }
//                    it
//                }
//                .compose(applySchedulersOnSingle())
//    }
//
//    fun getUserInfo():UserInfo{
//        return Hawk.get<UserInfo>(KEY_USER_INFO)
//    }
//
//    fun getImToken():String?{
//        return Hawk.get<String>(KEY_TOKEN_IM,null)
//    }
//
//    fun getSmsCode(phone: String):Single<Rep<String>>{
//        return userService.getSmsCode(SmsCodeReq(phone))
//                .compose(applySchedulersOnSingle())
//    }
//
//    fun getRegisterSmsCode(phone: String):Single<Rep<String>>{
//        return userService.getRegisterSmsCode(SmsCodeReq(phone))
//                .compose(applySchedulersOnSingle())
//    }
//
//    fun getResetSmsCode(phone: String):Single<Rep<String>>{
//        return userService.getResetSmsCode(SmsCodeReq(phone))
//                .compose(applySchedulersOnSingle())
//    }
//
//    fun quickLogin(phone: String,captcha:String): Single<Rep<Tokens>> {
//        return userService.quickLogin(QuickLoginReq(phone,captcha,sn = Build.SERIAL))
//                .map {
//                    if (it.isSuccessful()){
//                        Hawk.put(KEY_TOKEN, it.entry.login)
//                        Hawk.put(KEY_TOKEN_IM, it.entry.message)
//                    }
//                    it
//                }
//                .compose(applySchedulersOnSingle())
//    }
//

}
