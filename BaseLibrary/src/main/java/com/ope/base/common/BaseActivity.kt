package com.ope.base.common

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.alibaba.android.arouter.launcher.ARouter
import com.ope.base.view.ProgressLoading

import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator

open class BaseActivity : SupportActivity() {

    protected val scopeProvider by lazy { AndroidLifecycleScopeProvider.from(this) }

    lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
//

        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)

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