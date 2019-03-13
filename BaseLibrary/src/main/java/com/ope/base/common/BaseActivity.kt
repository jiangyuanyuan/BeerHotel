package com.ope.base.common

import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import com.alibaba.android.arouter.launcher.ARouter
import com.ope.base.data.ViewModelFactory
import com.ope.base.data.dto.RepCode
import com.ope.base.data.notice.ErrorNotice
import com.ope.base.view.ProgressLoading
import com.ope.provider.router.RouterPath

import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportFragment
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import javax.inject.Inject

open class BaseActivity : SupportActivity() , ErrorNotice.ErrorListener {
    override fun onNotify(code: String, msg: String) {
        Log.e("BaseActivity:  code  ",code)
        when(code){
            RepCode.TOKEN_EXPIRED.code -> start(ARouter.getInstance().build(RouterPath.MyCenter.PATH_LOGINFRAGMENT).navigation() as SupportFragment)
        }
    }

    protected val scopeProvider by lazy { AndroidLifecycleScopeProvider.from(this) }

    lateinit var mLoadingDialog: ProgressLoading

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
//

        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        if(this.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        mLoadingDialog = ProgressLoading.create(this)
        fragmentAnimator = DefaultHorizontalAnimator() //横向动画
//        fragmentAnimator = DefaultVerticalAnimator() //竖向动画
//        fragmentAnimator = DefaultNoAnimator() //没有动画
        setStatusBar()
    }

    private fun setStatusBar() {
        with(window) {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//            statusBarColor = Color.TRANSPARENT
        }
    }
}