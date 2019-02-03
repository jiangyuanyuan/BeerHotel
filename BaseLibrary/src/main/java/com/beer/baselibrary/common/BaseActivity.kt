package com.beer.baselibrary.common

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.beer.baselibrary.view.ProgressLoading
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator

open class BaseActivity : SupportActivity() {

    protected val scopeProvider by lazy { AndroidLifecycleScopeProvider.from(this) }

    lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mLoadingDialog = ProgressLoading.create(this)
        ARouter.getInstance().inject(this)

        fragmentAnimator = DefaultHorizontalAnimator() //横向动画
//        fragmentAnimator = DefaultVerticalAnimator() //竖向动画
//        fragmentAnimator = DefaultNoAnimator() //没有动画
    }
}