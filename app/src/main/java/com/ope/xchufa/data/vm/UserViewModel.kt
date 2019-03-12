package com.ope.xchufa.data.vm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.ope.base.KEY_TOKEN
import com.ope.base.KEY_USER_INFO
import com.ope.base.data.dto.Req
import com.ope.base.helper.DateUtils

import com.ope.base.helper.SingleLiveEvent
import com.ope.base.helper.ValidatorUtil
import com.ope.base.helper.loge
import com.ope.base.security.AESUtils
import com.ope.base.security.Base64
import com.ope.base.security.MD5Utils
import com.ope.xchufa.Country

import com.ope.xchufa.data.dto.UserInfo
import com.ope.xchufa.data.repository.UserRepository
import com.ope.xchufa.ext.getToken
import com.orhanobut.hawk.Hawk
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {



     val loginResult = SingleLiveEvent<UserInfo>()
    val logoutResult = SingleLiveEvent<Int>()
    val countryType = SingleLiveEvent<String>()

     val registerResult = SingleLiveEvent<UserInfo>()
     val verificationCodeResult = SingleLiveEvent<String>()



     val autoLoginResult = SingleLiveEvent<UserInfo>()

     val updateUserInfoResult = SingleLiveEvent<UserInfo>()
     val getSmsResult = SingleLiveEvent<Boolean>()
     val getRegisterSmsResult = SingleLiveEvent<Boolean>()
     val getResetSmsResult = SingleLiveEvent<Boolean>()
     val quickLoginResult = SingleLiveEvent<UserInfo>()


    fun getVerificationCode(mobile: String, shortPhone: String): LiveData<String>{
        userRepository.getVerificationCode(mobile,shortPhone).subscribe({
            verificationCodeResult.value = if (it.isSuccessful()) it.data.smsVerifyCode else null
        },{
            it.printStackTrace()
            verificationCodeResult.value = null
        })
        return verificationCodeResult
    }

    fun login(account: String, password: String,shortphone: String): LiveData<UserInfo>{
        val userType = if (ValidatorUtil.isMobile(account)) "USER_TYPE_MOBILE" else "USER_TYPE_USERNAME"
        val time = System.currentTimeMillis().toString()
        val passwordMd5 = MD5Utils.md5(password)
        val encryptAccount = Base64.encode(AESUtils.encrypt(account, Base64.encode(userType + time))!!)
        val passwordAccount = Base64.encode(AESUtils.encrypt(passwordMd5, Base64.encode(userType + time + account))!!)
        userRepository.login(encryptAccount,passwordAccount,account,userType,shortphone).subscribe({
            if (it.isSuccessful()){
                Hawk.put(KEY_TOKEN, it.data.account.userId.toString()+"_"+it.data.token)
                Hawk.put(KEY_USER_INFO, it.data)
                loginResult.value =it.data
            }else {
                loginResult.value = null
            }
        },{
            it.printStackTrace()
            loginResult.value = null
        })
        return loginResult
    }


    fun rigster(userName: String,account: String, password: String,shortphone: String): LiveData<UserInfo>{
//        val userType = if (ValidatorUtil.isMobile(account)) "USER_TYPE_MOBILE" else "USER_TYPE_USERNAME"
        val userType = "USER_TYPE_MOBILE" //只支持
        val time = DateUtils.getUTCTime().time.toString()
        val passwordMd5 = MD5Utils.md5(password)
        val encryptAccount = Base64.encode(AESUtils.encrypt(account, Base64.encode(userType + time))!!)
        val passwordAccount = Base64.encode(AESUtils.encrypt(passwordMd5, Base64.encode(userType + time + account))!!)
        userRepository.rigster(userName,encryptAccount,passwordAccount,userType,shortphone).subscribe({
            if (it.isSuccessful()){
                Hawk.put(KEY_USER_INFO, it.data)
                loginResult.value =  it.data
            }else {
                loginResult.value = null
            }
        },{
            it.printStackTrace()

        })
        return loginResult
    }

    fun logout(): LiveData<Int>{
        userRepository.logout().subscribe({
             if (it.isSuccessful()){
                 logoutResult.value =it.data
                 Hawk.delete(KEY_TOKEN)
//                 Hawk.delete(KEY_USER_INFO)
             } else {
                 logoutResult.value = null
             }

        },{
            it.printStackTrace()
            logoutResult.value = null
        })
        return logoutResult
    }


    fun resetPwd(account:String, password: String,shortphone: String): LiveData<Int>{
        val userType = if (ValidatorUtil.isMobile(account)) "USER_TYPE_MOBILE" else "USER_TYPE_USERNAME"
        val time = DateUtils.getUTCTime().time.toString()
        val passwordMd5 = MD5Utils.md5(password)
        val encryptAccount = Base64.encode(AESUtils.encrypt(account, Base64.encode(userType + time))!!)
        val passwordAccount = Base64.encode(AESUtils.encrypt(passwordMd5, Base64.encode(userType + time + account))!!)
        userRepository.resetPwd(encryptAccount,passwordAccount,userType,shortphone).subscribe({
            if (it.isSuccessful()){
                logoutResult.value =it.data
                Hawk.delete(KEY_TOKEN)
                Hawk.delete(KEY_USER_INFO)
            } else {
                logoutResult.value = null
            }

        },{
            it.printStackTrace()
            logoutResult.value = null
        })
        return logoutResult
    }




//
//    fun register(phone: String, password: String,captcha: String): LiveData<Rep<RegisterFragment>> {
//        userRepository.register(phone, password.md5(),captcha, Build.SERIAL)
//                .subscribe({
//                    registerResult.value = it
//                }, {
//                    it.printStackTrace()
//                    registerResult.value = null
//                })
//        return registerResult
//    }
//
//    fun resetPwd(phone: String, password: String,captcha: String): LiveData<Boolean> {
//        userRepository.resetPwd(phone, password.md5(),captcha)
//                .subscribe({
//                    resetPwdResult.value = it.isSuccessful()
//                }, {
//                    it.printStackTrace()
//                    resetPwdResult.value = false
//                })
//        return resetPwdResult
//    }
//
//
//    fun autoLogin(token: String): LiveData<UserInfo> {
//        userRepository.verifyToken(token)
//                .subscribe({
//                    if (it.isSuccessful()) {
//                        userRepository.refreshToken()
//                                .subscribe({
//                                    if (it.isSuccessful()) {
//                                        userRepository.getLoginUser()
//                                                .subscribe({
//                                                    if (it.isSuccessful()) {
//                                                        autoLoginResult.value = it.entry
//                                                    } else {
//                                                        autoLoginResult.value = null
//                                                    }
//                                                }, {
//                                                    it.printStackTrace()
//                                                    autoLoginResult.value = null
//                                                })
//                                    } else {
//                                        autoLoginResult.value = null
//                                    }
//                                }, {
//                                    it.printStackTrace()
//                                    autoLoginResult.value = null
//                                })
//                    } else {
//                        autoLoginResult.value = null
//                    }
//                }, {
//                    it.printStackTrace()
//                    autoLoginResult.value = null
//                })
//        return autoLoginResult
//    }
//
//    fun logout(): LiveData<Boolean> {
//        userRepository.logout()
//                .subscribe({
//                    logoutResult.value = it.isSuccessful()
//                }, {
//                    it.printStackTrace()
//                    logoutResult.value = false
//                })
//        return logoutResult
//    }
//
//    fun updateUserInfo(userInfo: UserInfo): LiveData<UserInfo> {
//        userRepository.updateUserInfo(userInfo)
//                .subscribe({
//                    if (it.isSuccessful()) {
//                        updateUserInfoResult.value = it.entry
//                    } else {
//                        updateUserInfoResult.value = null
//                    }
//                }, {
//                    it.printStackTrace()
//                    updateUserInfoResult.value = null
//                })
//        return updateUserInfoResult
//    }
//
//    fun getUserInfo(): UserInfo {
//        return userRepository.getUserInfo()
//    }
//
//    fun getImToken(): String? {
//        return userRepository.getImToken()
//    }
//
//    fun getSmsCode(phone: String): LiveData<Boolean> {
//        userRepository.getSmsCode(phone)
//                .subscribe({
//                    getSmsResult.value = it.isSuccessful()
//                }, {
//                    it.printStackTrace()
//                    getSmsResult.value = null
//                })
//        return getSmsResult
//    }
//
//    fun getRegisterSmsCode(phone: String): LiveData<Boolean> {
//        userRepository.getRegisterSmsCode(phone)
//                .subscribe({
//                    getRegisterSmsResult.value = it.isSuccessful()
//                }, {
//                    it.printStackTrace()
//                    getRegisterSmsResult.value = null
//                })
//        return getRegisterSmsResult
//    }
//
//    fun getResetSmsCode(phone: String): LiveData<Boolean> {
//        userRepository.getResetSmsCode(phone)
//                .subscribe({
//                    getResetSmsResult.value = it.isSuccessful()
//                }, {
//                    it.printStackTrace()
//                    getResetSmsResult.value = null
//                })
//        return getResetSmsResult
//    }
//
//    fun quickLogin(phone: String, captcha: String): LiveData<UserInfo> {
//        userRepository.quickLogin(phone, captcha)
//                .subscribe({
//                    if (it.isSuccessful()){
//                        // 第一次登录成功后,就默认勾选了用户协议
//                        Hawk.put(KEY_CLAUSE, true)
//                        userRepository.getLoginUser()
//                                .subscribe({
//                                    if (it.isSuccessful()){
//                                        quickLoginResult.value = it.entry
//                                    }else{
//                                        quickLoginResult.value = null
//                                    }
//                                },{
//                                    it.printStackTrace()
//                                    quickLoginResult.value = null
//                                })
//                    }else{
//                        quickLoginResult.value = null
//                    }
//                }, {
//                    it.printStackTrace()
//                    quickLoginResult.value = null
//                })
//        return quickLoginResult
//    }

}