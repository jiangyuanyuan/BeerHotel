package com.ope.xchufa.func.trade

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ope.base.common.BaseFragment

import com.ope.base.helper.stripZeros
import com.ope.provider.router.RouterPath

import com.ope.xchufa.R
import com.ope.xchufa.data.dto.Order
import com.ope.xchufa.data.vm.TradeViewModel
import com.ope.xchufa.injection.component.DaggerAppComponent
import com.ope.xchufa.injection.mTradeHistoryFragment
import kotlinx.android.synthetic.main.fragment_purchase_detail.*
import me.yokeyword.fragmentation.SupportActivity


@Route(path = RouterPath.Trade.PATH_PURCHASEDETAIL)
class PurchaseDetailFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_purchase_detail

    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }

    private lateinit var mTradeViewModel: TradeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mTradeViewModel = ViewModelProviders.of(_mActivity, viewModelFactory).get(TradeViewModel::class.java)
        mHeaderBar.onBackClick(View.OnClickListener {
            (activity as SupportActivity).startWithPop(mTradeHistoryFragment)
        })

        if (mTradeViewModel?.orderResult.value!=null) setDate(mTradeViewModel?.orderResult.value!!)

    }

    private fun setDate(order: Order) {
        mNumber?.text = order?.otcOrderId?.toString()
        mBusiness?.text = mTradeViewModel?.purchaseItemResult.value?.saleIntro?.userName
        mTransactionNumber?.text = order?.amount?.stripZeros()
        mTransactionUnit?.text = order?.price?.stripZeros()
        mTransactionTime?.text = order?.createTime.toString()
        mTotalPrice?.text = "合计："+order?.totalAmount?.stripZeros()+mTradeViewModel?.purchaseItemResult?.value?.adOrder?.currencyCode
        mPayeeName?.text = mTradeViewModel?.purchaseItemResult.value?.saleIntro?.userName
        mPayeeAccount?.text = mTradeViewModel?.purchaseItemResult.value?.saleIntro?.email
    }
}