package com.ope.xchufa.func.exchange

import com.alibaba.android.arouter.facade.annotation.Route
import com.ope.base.common.BaseFragment
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import com.ope.xchufa.injection.component.DaggerAppComponent


@Route(path = RouterPath.Exchange.PATH_EXCHANGE)
class ExchangeFragment :BaseFragment(){

    override fun getLayoutId()= R.layout.fragment_exchange
    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }


}