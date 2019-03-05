package com.ope.xchufa

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter

import com.orhanobut.hawk.Hawk
import me.yokeyword.fragmentation.Fragmentation


open class App : Application() {

    companion object {
        lateinit var INSTANCE: App

    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        if (BuildConfig.DEBUG) {
            Fragmentation.builder()
                    // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                    .stackViewMode(Fragmentation.BUBBLE)
                    .debug(true) // 实际场景建议.debug(BuildConfig.DEBUG)
                    /**
                     * 可以获取到[me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning]
                     * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                     */
                    .handleException {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                    .install()
        }

        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
        Hawk.init(this).build()


    }


    fun exitApp(){
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.killBackgroundProcesses(packageName)
        System.exit(0)
    }

}