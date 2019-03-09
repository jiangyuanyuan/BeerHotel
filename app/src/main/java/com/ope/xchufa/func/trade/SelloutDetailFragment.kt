package com.ope.xchufa.func.trade

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ope.base.common.BaseFragment
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import com.ope.xchufa.injection.component.DaggerAppComponent
import kotlinx.android.synthetic.main.fragment_sellout_detail.*


@Route(path = RouterPath.Trade.PATH_SELLOUTDETAIL)
class SelloutDetailFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_sellout_detail

    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mHeaderBar.onBackClick(View.OnClickListener {
            pop()
        })
    }
}