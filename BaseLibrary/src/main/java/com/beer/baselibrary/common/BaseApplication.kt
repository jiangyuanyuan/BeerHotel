package com.beer.baselibrary.common

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.beer.baselibrary.BuildConfig
import com.orhanobut.hawk.Hawk

open class BaseApplication : Application() {

//    lateinit var appComponent: AppComponent

    companion object {
        lateinit var INSTANCE: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
//        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
        Hawk.init(this).build()
    }

}