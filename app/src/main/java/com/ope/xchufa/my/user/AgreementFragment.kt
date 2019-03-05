package com.ope.xchufa.my.user

import com.alibaba.android.arouter.facade.annotation.Route
import com.ope.base.common.BaseFragment
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import com.ope.xchufa.injection.component.DaggerAppComponent


@Route(path = RouterPath.MyCenter.PATH_AGREEMENT)
class AgreementFragment : BaseFragment(){
    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }

    override fun getLayoutId() = R.layout.fragment_agreement
}