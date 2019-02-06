package com.beer.beerhotel.data.vm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.os.Build
import com.beer.baselibrary.data.dto.Rep
import com.beer.baselibrary.helper.SingleLiveEvent
import com.beer.baselibrary.helper.md5
import com.beer.beerhotel.data.dto.UserInfo
import com.beer.beerhotel.data.repository.UserRepository
import com.orhanobut.hawk.Hawk
import java.io.File
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {


//    private val registerResult = SingleLiveEvent<Rep<Register>>()
    private val resetPwdResult = SingleLiveEvent<Boolean>()
    private val loginResult = SingleLiveEvent<UserInfo>()
    private val autoLoginResult = SingleLiveEvent<UserInfo>()
    private val logoutResult = SingleLiveEvent<Boolean>()
    private val updateUserInfoResult = SingleLiveEvent<UserInfo>()
    private val getSmsResult = SingleLiveEvent<Boolean>()
    private val getRegisterSmsResult = SingleLiveEvent<Boolean>()
    private val getResetSmsResult = SingleLiveEvent<Boolean>()
    private val quickLoginResult = SingleLiveEvent<UserInfo>()

//    fun login(phone: String, password: String): LiveData<UserInfo> {
//        userRepository.getValidateCode()
//                .subscribe({
//                    if (it.isSuccessful()) {
//                        userRepository.login(LoginReq(phone, (password.md5() + it.entry).md5(), it.entry,sn = Build.SERIAL))
//                                .subscribe({
//                                    if (it.isSuccessful()) {
//                                        // 第一次登录成功后,就默认勾选了用户协议
//                                        Hawk.put(KEY_CLAUSE, true)
//                                        userRepository.getLoginUser()
//                                                .subscribe({
//                                                    if (it.isSuccessful()) {
//                                                        loginResult.value = it.entry
//                                                    } else {
//                                                        loginResult.value = null
//                                                    }
//                                                },{
//                                                    it.printStackTrace()
//                                                    loginResult.value = null
//                                                })
//                                    } else {
//                                        loginResult.value = null
//                                    }
//                                },{
//                                    it.printStackTrace()
//                                    loginResult.value = null
//                                })
//                    } else {
//                        loginResult.value = null
//                    }
//                },{
//                    it.printStackTrace()
//                    loginResult.value = null
//                })
//        return loginResult
//    }
//
//    fun register(phone: String, password: String,captcha: String): LiveData<Rep<Register>> {
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