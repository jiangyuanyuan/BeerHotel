package com.ope.base.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.app.Dialog
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.telephony.TelephonyManager
import android.util.DisplayMetrics
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import com.ope.base.view.DefaultTextWatcher
import java.math.BigDecimal
import java.util.*


@SuppressLint("MissingPermission")
fun Context.isConnected(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnected
}

fun Context.getScreenWH(): Array<Int> {
    val dm = resources.displayMetrics
    var heigth = dm.heightPixels
    var width = dm.widthPixels
    return arrayOf(width, heigth)
}

fun Context.setTopTextView(): TextView? {
    if (this is Activity) {
        var text = TextView(this)
        text?.setLineSpacing(1.5f, 1.5f)
        text?.setTextColor(ActivityCompat.getColor(this, android.R.color.holo_red_light))
        val pl = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        pl.setMargins(0, 40, 0, 0)
        (window.decorView as FrameLayout).addView(text, pl)
        return text
    }
    return null
}

/*
    扩展Button可用性
 */
fun Button.enable(et: EditText, method: () -> Boolean){
    val btn = this
    et.addTextChangedListener(object : DefaultTextWatcher(){
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}
/*
    扩展Button可用性
 */
fun Button.enable(ckb: CheckBox, method: () -> Boolean){
    val btn = this
    ckb.setOnCheckedChangeListener { buttonView, isChecked ->
        btn.isEnabled = method()
    }
}

fun Context.needUpdateLocale(pNewUserLocale: Locale): Boolean {
    val _Locale: Locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { //7.0有多语言设置获取顶部的语言
        resources.configuration.locales.get(0)
    } else {
        resources.configuration.locale
    }
    return _Locale != pNewUserLocale
}

fun Context.updateLocale(pNewUserLocale: Locale) {
    if (needUpdateLocale(pNewUserLocale)) {
        val _Configuration = resources.configuration
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            _Configuration.setLocale(pNewUserLocale)
        } else {
            _Configuration.locale = pNewUserLocale
        }
        resources.updateConfiguration(_Configuration, resources.displayMetrics)
    }
}

@SuppressLint("InvalidWakeLockTag")
fun Context.brightScreen() {
    //获取电源管理器对象
    try {
        var pm = this.getSystemService(Context.POWER_SERVICE) as PowerManager
        var mWakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP or
                PowerManager.SCREEN_BRIGHT_WAKE_LOCK or
                PowerManager.ON_AFTER_RELEASE, "SimpleTimer")
        var screenOffTime = 5 * 60 * 1000
        try {
            screenOffTime = Settings.System.getInt(contentResolver,
                    Settings.System.SCREEN_OFF_TIMEOUT)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        mWakeLock.acquire(screenOffTime.toLong())
//        var c = pm.javaClass
//        val wakeUp = c.getMethod("wakeUp", Long::class.java)
//        wakeUp.isAccessible = true
//        wakeUp.invoke(pm, 1000L)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun View.showKeyboard() {
    this.requestFocus()
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun Activity.showToast(text: String) {
    runOnUiThread { Toast.makeText(this, text, Toast.LENGTH_SHORT).show() }
}

fun BigDecimal?.stripZeros(): String {
    return if (this?.compareTo(BigDecimal.ZERO) == 0) "0" else (this?.stripTrailingZeros()?.toPlainString()
            ?: "0")
}

fun <T> List<T>?.getItemOrNull(i: Int): T? {
    return if (this == null) {
        null
    } else {
        if (this.size > i) {
            this[i]
        } else {
            null
        }
    }
}

fun View.hideKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (imm.isActive) {
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}

fun String.beforeZero(): String {
    return if (this.length == 1) "0$this" else this
}

fun getProperty(key: String, defaultValue: String): String {
    var value = defaultValue
    try {
        val c = Class.forName("android.os.SystemProperties")
        val get = c.getMethod("get", String::class.java, String::class.java)
        value = get.invoke(c, key, "unknown") as String
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        return value
    }
}

fun Context.getPackageInfo(packageID: String): ApplicationInfo? {
    return try {
        packageManager.getPackageInfo(packageID, 0).applicationInfo
    } catch (e: Exception) {
        null
    }
}

fun Context.startApp(info: ApplicationInfo?) {
    try {
        val intent = packageManager.getLaunchIntentForPackage(info?.packageName)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    } catch (e: Exception) {
    }
}

fun Context.startWebApp(url: String) {
    try {
        val intent = Intent()
        intent.action = "android.intent.action.VIEW"
        val contentUrl = Uri.parse(url)
        intent.data = contentUrl
        startActivity(intent)
    } catch (e: Exception) {
    }
}


fun Context.getPhoneNumber():String{
    val tm = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    return tm.line1Number//获取本机号码

}

fun Activity.getDeviceID():String{
    val tm = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    return tm.getDeviceId()
}

fun  Activity.getAppProcessName():String {
    //当前应用pid
    val pid = android.os.Process.myPid()
    //任务管理类
    val manager = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    //遍历所有应用
    manager.runningAppProcesses?.forEach {
        if (it.pid == pid)//得到当前应用
            return it.processName//返回包名
    }
    return ""
}

/**
 * 是否连接wifi
 */
fun Context.isConnectWifi():Boolean{
    val connManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val info = connManager.activeNetworkInfo
    return (info != null && info.isConnected && info.type == ConnectivityManager.TYPE_WIFI)
}

fun String.md5() = md5(this)


fun Dialog.bottomShow():Unit{
    this.show()
    val window = this.window
    window!!.setGravity(Gravity.BOTTOM)
    val lp = window.attributes
    val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val dm = DisplayMetrics()
    manager.defaultDisplay.getMetrics(dm)
    val width = dm.widthPixels
    lp.width = width
    window.attributes = lp

}

inline fun <reified T : ViewModel> FragmentActivity.getViewModel() = ViewModelProviders.of(this).get(T::class.java)

inline fun <reified T : ViewModel> Fragment.getViewModel() = ViewModelProviders.of(this).get(T::class.java)

inline fun <reified T : ViewModel> Fragment.getActivityViewModel() = ViewModelProviders.of(activity!!).get(T::class.java)

inline fun <reified T : ViewGroup.LayoutParams> View.getParams() = this.layoutParams as T