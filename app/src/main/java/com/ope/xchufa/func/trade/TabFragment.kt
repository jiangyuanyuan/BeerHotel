package com.ope.xchufa.func.trade

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.ope.base.easy.EasyAdapter
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import kotlinx.android.synthetic.main.fragment_tab.*

import kotlinx.android.synthetic.main.item_trade_tab.view.*
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportActivity
import android.support.v7.widget.DividerItemDecoration
import com.ope.base.common.BaseFragment
import com.ope.base.helper.loge
import com.ope.base.helper.stripZeros
import com.ope.xchufa.AD_PURCHASE_TYPE
import com.ope.xchufa.AD_SELLOUT_TYPE

import com.ope.xchufa.PayType
import com.ope.xchufa.data.dto.Content
import com.ope.xchufa.data.vm.TradeViewModel
import com.ope.xchufa.injection.component.DaggerAppComponent
import me.yokeyword.fragmentation.SupportFragment


@SuppressLint("ValidFragment")
class TabFragment(id: String,name:String) : BaseFragment() {
    override fun getLayoutId() = R.layout.fragment_tab

    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }
    private val mPurchaseFragment: SupportFragment by lazy {
        ARouter.getInstance().build(RouterPath.Trade.PATH_PURCHASE).navigation() as SupportFragment
    }
    private val mSelloutFragment: SupportFragment by lazy {
        ARouter.getInstance().build(RouterPath.Trade.PATH_SELLOUT).navigation() as SupportFragment
    }

    private var assetId: String = id
    private var name: String = name
    private var mList: List<Content>? = null
    private lateinit var mTradeViewModel: TradeViewModel
    private lateinit var mAdapter: EasyAdapter<Content>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mTradeViewModel = ViewModelProviders.of(_mActivity, viewModelFactory).get(TradeViewModel::class.java)

        initAdpter()
        //监听RadioGroup的变化
        mTradeViewModel.adTypeResult.observe(_mActivity, Observer {
            getAdOrderList()
        })
        getAdOrderList()
    }

    private fun initAdpter() {
        mRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mAdapter = EasyAdapter<Content>(R.layout.item_trade_tab, { itemView, _, item ->
            itemView.mUserName.text = item?.saleIntro?.userName ?: ""
            itemView.mStock.text = "库存：" + item?.adOrder?.totalAmount?.stripZeros() + " "+name
            itemView.mUnitPrice.text = "单价：" + item?.adOrder?.price?.stripZeros()+item?.adOrder?.currencyCode
            itemView.mQuota.text = "单笔限额：" + item?.adOrder?.minTxAmount.stripZeros() + "~" + item?.adOrder?.maxTxAmount.stripZeros() + item?.adOrder?.currencyCode
            itemView.mAliPayIcon.visibility = if (item?.payInfos.any { it.payType == PayType.PAY_TYPE_ALI }) View.VISIBLE else View.GONE
            itemView.mWeChatPayIcon.visibility = if (item?.payInfos.any { it.payType == PayType.PAY_TYPE_WECHAT }) View.VISIBLE else View.GONE
            itemView.mBankPayIcon.visibility = if (item?.payInfos.any { it.payType == PayType.PAY_TYPE_BANK }) View.VISIBLE else View.GONE

            if (mTradeViewModel.adTypeResult.value == AD_SELLOUT_TYPE) {
                itemView.mPurchase.visibility = View.GONE
                itemView.mSellout.visibility = View.VISIBLE
            } else {
                itemView.mPurchase.visibility = View.VISIBLE
                itemView.mSellout.visibility = View.GONE
            }


            itemView.mPurchase.setOnClickListener {
                mTradeViewModel.purchaseItemResult.value = item
                (activity as SupportActivity).start(mPurchaseFragment)
            }
            itemView.mSellout.setOnClickListener {
                mTradeViewModel.selloutItemResult.value = item
                (activity as SupportActivity).start(mSelloutFragment)
            }

        }, mList ?: emptyList())
        mRecyclerView.adapter = mAdapter
    }

    private fun getAdOrderList() {
        if (assetId != null && assetId.isNotBlank()) {
            mTradeViewModel.getAdOrderList(assetId, mTradeViewModel.adTypeResult?.value?.toString()
                    ?: AD_PURCHASE_TYPE).observe(_mActivity, Observer {
                mList = it?.content ?: emptyList()
                mAdapter.submitList(mList!!)
            })
        } else {

        }
    }
}