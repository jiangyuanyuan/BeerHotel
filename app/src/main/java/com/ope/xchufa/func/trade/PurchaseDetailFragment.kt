package com.ope.xchufa.func.trade

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ope.base.common.BaseFragment
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import com.ope.xchufa.injection.component.DaggerAppComponent
import com.ope.xchufa.injection.mTradeHistoryFragment
import kotlinx.android.synthetic.main.fragment_purchase.*
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportFragment


@Route(path = RouterPath.Trade.PATH_PURCHASEDETAIL)
class PurchaseDetailFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_purchase_detail

    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mHeaderBar.onBackClick(View.OnClickListener {
            (activity as SupportActivity).startWithPop(mTradeHistoryFragment)
        })
    }
}