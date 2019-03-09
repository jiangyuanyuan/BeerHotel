package com.ope.xchufa


import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter

import com.ope.base.common.BaseActivity
import com.ope.provider.router.RouterPath
import me.yokeyword.fragmentation.ISupportFragment


@Route(path = RouterPath.Main.PATH_MAIN)
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadRootFragment(R.id.mainContentFl, ARouter.getInstance().build(RouterPath.Main.PATH_MAIN_FRAGMENT).navigation() as ISupportFragment)
    }

    override fun onDestroy() {
        super.onDestroy()

    }


}
