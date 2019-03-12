package com.ope.base.helper

import android.app.Activity
import android.content.Context
import android.telephony.TelephonyManager

import java.util.Locale
import android.util.DisplayMetrics
import android.content.pm.PackageManager
import android.R.attr.versionName
import android.content.pm.PackageInfo





/**
 * 系统工具类
 * Created by zhuwentao on 2016-07-18.
 */
object SystemUtil {

    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    val systemLanguage: String
        get() = Locale.getDefault().language

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return  语言列表
     */
    val systemLanguageList: Array<Locale>
        get() = Locale.getAvailableLocales()

    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    val systemVersion: String
        get() = android.os.Build.VERSION.RELEASE

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    val systemModel: String
        get() = android.os.Build.MODEL

    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    val deviceBrand: String
        get() = android.os.Build.BRAND

    /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     *
     * @return  手机IMEI
     */
    fun getIMEI(ctx: Context): String? {
        val tm = ctx.getSystemService(Activity.TELEPHONY_SERVICE) as TelephonyManager
        return tm?.deviceId
    }

    fun getScreen(ctx: Activity):String{
        val dm = DisplayMetrics()
        ctx.windowManager.defaultDisplay.getMetrics(dm)
        val widthPixels = dm.widthPixels
        val heightPixels = dm.heightPixels
        return "$widthPixels*$heightPixels"
    }

    /**
     * 获取版本名称
     *
     * @param context 上下文
     *
     * @return 版本名称
     */
    fun getVersionName(context: Context): String {

        //获取包管理器
        val pm = context.packageManager
        //获取包信息
        try {
            val packageInfo = pm.getPackageInfo(context.packageName, 0)
            //返回版本号
            return packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return ""

    }
}
