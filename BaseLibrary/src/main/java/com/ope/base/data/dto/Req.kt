package com.ope.base.data.dto

import android.app.Activity
import android.content.Context
import com.ope.base.KEY_TOKEN
import com.ope.base.helper.SystemUtil
import com.ope.base.helper.getAppProcessName
import com.ope.base.helper.getDeviceID
import com.orhanobut.hawk.Hawk

var APPID = ""
var DEVICEID = ""
var OS = "android"
var OSVERSION = ""
var SCREENSIZE = ""
var APIVERSION = ""
var REQUESTID = ""


open class Req(val appId: String = APPID,
                   val deviceID: String = DEVICEID,
                   val os: String = OS,
                   val osVersion: String= OSVERSION,
                   val screenSize: String= SCREENSIZE,
                   val timestamp: String= System.currentTimeMillis().toString(),
                   val apiVersion: String= APIVERSION,
                   val requestId: String= REQUESTID,
                   val token:String = Hawk.get<String>(KEY_TOKEN)?:""
               ): HashMap<String, String>(){
    init {
        putParams("AppID",appId)
        putParams("DeviceID",deviceID)
        putParams("Os",os)
        putParams("OsVersion",osVersion)
        putParams("ScreenSize",screenSize)
        putParams("Timestamp",timestamp)
        putParams("ApiVersion",apiVersion)
        putParams("RequestId",requestId)
        putParams("token",token)
    }


     fun putParams(key:String, value:String) {
        if (key!=null&&key?.isNotBlank()&&value!=null&&value?.isNotBlank()){
            put(key, value)
        }

    }

}