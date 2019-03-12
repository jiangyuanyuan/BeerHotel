package com.ope.xchufa


import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter

import com.ope.base.common.BaseActivity
import com.ope.provider.router.RouterPath
import com.ope.xchufa.ext.isLogin
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportFragment


@Route(path = RouterPath.Main.PATH_MAIN)
class MainActivity : BaseActivity() {


    private val mLoginFragment: SupportFragment by lazy {
        ARouter.getInstance().build(RouterPath.MyCenter.PATH_LOGINFRAGMENT)
                .navigation() as SupportFragment
    }

    private val mMainFragment: SupportFragment by lazy {
        ARouter.getInstance().build(RouterPath.Main.PATH_MAIN_FRAGMENT)
                .navigation() as SupportFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (isLogin()){
            loadRootFragment(R.id.mainContentFl,mMainFragment)
        }else {
            loadRootFragment(R.id.mainContentFl, mLoginFragment)
        }

    }

}
