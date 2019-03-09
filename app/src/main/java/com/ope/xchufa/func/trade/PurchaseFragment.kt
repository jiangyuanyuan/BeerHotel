package com.ope.xchufa.func.trade

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jakewharton.rxbinding2.view.RxView
import com.ope.base.common.BaseFragment
import com.ope.base.helper.applyWidgetSchedulers
import com.ope.base.view.OneTextDialog
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import com.ope.xchufa.injection.component.DaggerAppComponent
import com.ope.xchufa.injection.mTradeDetailFragment
import com.ope.xchufa.injection.mTradeHistoryFragment
import kotlinx.android.synthetic.main.fragment_purchase.*
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportFragment


@Route(path = RouterPath.Trade.PATH_PURCHASE)
class PurchaseFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_purchase

    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mHeaderBar.onBackClick(View.OnClickListener {
            (activity as SupportActivity).startWithPop(mTradeHistoryFragment)
        })

        RxView.clicks(mPurchaseExchange60)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (activity as SupportActivity).startWithPop(mTradeHistoryFragment)
                }

        RxView.clicks(mPurchaseOk)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (activity as SupportActivity).startWithPop(mTradeDetailFragment)
                }

        RxView.clicks(mPurchaseExchange60)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    OneTextDialog.Builder(activity!!).message("超过2000 CNY的订单需要高级认证").no("知道了").build()
                            .setNoListener { it.dismiss() }.show()
                }

    }
}