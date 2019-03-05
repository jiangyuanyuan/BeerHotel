package com.ope.xchufa

import com.alibaba.android.arouter.facade.annotation.Route
import com.ope.base.common.BaseFragment
import com.ope.provider.router.RouterPath
import com.ope.xchufa.injection.component.DaggerAppComponent

@Route(path = RouterPath.Main.PATH_MAIN_FRAGMENT)
class MainFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_main

    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }
}