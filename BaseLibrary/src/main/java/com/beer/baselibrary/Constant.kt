@file:JvmName("C")

package com.beer.baselibrary

import com.orhanobut.hawk.Hawk
import java.util.*



//const val DEBUG_API_BASE_URL = "http://192.168.8.106:8888/"
const val DEBUG_API_BASE_URL = "http://120.78.174.86:8888/"
//var DEBUG_PRINTER_IP = Hawk.get<String>(PRINTER_IP, "192.168.1.100")  // 打印机ip
//var DEBUG_API_BASE_URL = Hawk.get<String>(CUSTOM_API_URL_KEY, "http://121.196.210.198/")  // 二代测试
//var DEBUG_API_BASE_URL_FOR_NLU = Hawk.get<String>(CUSTOM_API_NLU_URL_KEY, "http://118.31.54.89:8080/")// nlu 二代测试


const val KEY_TOKEN = "KEY_TOKEN"
const val KEY_TOKEN_IM = "KEY_TOKEN_IM"
const val KEY_USER_INFO = "KEY_USER_INFO"
const val KEY_CLAUSE = "KEY_CLAUSE"
const val KEY_WIFI_PHOTO = "KEY_WIFI_PHOTO"
const val KEY_TONE_PHOTO = "KEY_TONE_PHOTO"
//const val KEY_LANGUAGE = "KEY_LANGUAGE"
//const val KEY_USER = "KEY_USER"
//const val KEY_MEALS_NUMBER = "KEY_MEALS_NUMBER"
//const val KEY_TO_APP_ACCOUNT = "KEY_TO_APP_ACCOUNT"

// TOKEN有效时间7天，安全着想减去5分钟
const val KEY_TOKEN_VALID_TIME : Long = 7 * 24 * 60 * 60 * 1000 - 5 * 60 * 1000
