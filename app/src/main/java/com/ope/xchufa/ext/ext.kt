package com.ope.xchufa.ext

import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorManager
import android.media.RingtoneManager
import com.ope.base.KEY_TONE_PHOTO

import com.orhanobut.hawk.Hawk

fun Context.isSupportStepCountSensor(): Boolean {
    val manager = this.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    return manager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null || manager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null
}

fun Context.getVersionCode(): Int {
    val manager = this.packageManager
    var code = 0
    try {
        val info = manager.getPackageInfo(this.packageName,0)
        code = info.versionCode
    }catch (e: PackageManager.NameNotFoundException){
        e.printStackTrace()
    }
    return code
}

fun Context.getAppTotalSize(): String {
    val manager = this.packageManager
    var code = ""
    try {
        val info = manager.getPackageInfo(this.packageName,0)
        code = info.versionName
    }catch (e: PackageManager.NameNotFoundException){
        e.printStackTrace()
    }
    return code
}

/**
 * 播放系统提示音
 */
fun Context.play(){
    if (!Hawk.get<Boolean>(KEY_TONE_PHOTO,false)){
        return
    }
    val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    val rt = RingtoneManager.getRingtone(this,uri)
    if (!rt.isPlaying){
        rt.play()
    }
}
fun monthAddPrefix0(month: Int): String {
    if (month < 10) {
        return "0$month"
    }
    return "$month"
}