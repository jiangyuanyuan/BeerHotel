package com.ope.xchufa.data.repository

import com.ope.base.data.dto.Rep
import com.ope.base.helper.applySchedulersOnSingle
import com.ope.xchufa.data.api.UserService
import io.reactivex.Single
import javax.inject.Inject

class UserRepository @Inject constructor(private val userService: UserService) {

    fun getValidateCode(): Single<Rep<String>> {
        return userService.getValidateCode()
                .compose(applySchedulersOnSingle())
    }

//    fun register(phone: String, password: String,captcha: String,sn: String): Single<Rep<RegisterFragment>> {
//        return userService.register(RegisterReq(phone, password,captcha,sn))
//                .compose(applySchedulersOnSingle())
//    }
//
//    fun login(loginReq: LoginReq): Single<Rep<Tokens>> {
//        return userService.login(loginReq)
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
//    fun logout(): Single<Rep<Any>> {
//        return userService.logout()
//                .map {
//                    // 退出登录时,需清除的本地数据
//                    Hawk.delete(KEY_TOKEN)
//                    Hawk.delete(KEY_TOKEN_IM)
//                    Im.INSTANCE.disconnect()
//                    HealthHelper.clear()
//                    it
//                }
//                .compose(applySchedulersOnSingle())
//    }
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
//    fun resetPwd(phone: String, password: String,captcha: String): Single<Rep<String>> {
//        return userService.resetPwd(ResetPwdReq(phone, captcha, password))
//                .compose(applySchedulersOnSingle())
//    }applySchedulersOnSingle

}
