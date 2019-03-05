package com.ope.xchufa.my.user

import com.alibaba.android.arouter.facade.annotation.Route
import com.ope.base.common.BaseFragment
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import com.ope.xchufa.injection.component.DaggerAppComponent

@Route(path = RouterPath.MyCenter.PATH_RETRIEVEPWD)
class RetrievePwdFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_retrievepwd

    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }
}