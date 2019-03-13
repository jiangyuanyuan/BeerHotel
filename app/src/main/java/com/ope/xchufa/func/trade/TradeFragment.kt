package com.ope.xchufa.func.trade

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ope.base.common.BaseFragment
import com.ope.base.easy.ViewPagerAdapter
import com.ope.base.helper.loge
import com.ope.provider.router.RouterPath
import com.ope.xchufa.AD_PURCHASE_TYPE
import com.ope.xchufa.AD_SELLOUT_TYPE
import com.ope.xchufa.R
import com.ope.xchufa.data.vm.TradeViewModel
import com.ope.xchufa.injection.component.DaggerAppComponent
import kotlinx.android.synthetic.main.fragment_trade.*
import me.yokeyword.fragmentation.SupportFragment

@Route(path = RouterPath.Trade.PATH_TRADE)
class TradeFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_trade

    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }
    private val mList = mutableListOf<Fragment>()
    private lateinit var mTradeViewModel: TradeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mTradeViewModel = ViewModelProviders.of(_mActivity,viewModelFactory).get(TradeViewModel::class.java)
        mTabs.setupWithViewPager(mTradeViewpager)
        mTradeViewModel.getAssetsList().observe(this, Observer { list->
            if (list?.size!! >0){
                mList.clear()
                list.forEach {
                    mList.add(TabFragment(it?.assetId?.toString(),it.nameEn))
                    mTabs.addTab(mTabs.newTab())
                }
                mTradeViewpager.adapter = ViewPagerAdapter(childFragmentManager,mList)
                for (i in 0 until mList.size) {
                    mTabs.getTabAt(i)?.text = list[i].nameEn
                }
            }else {

            }
        })

        mSelloutPurRG.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.mPurchaseRB -> {
                    mTradeViewModel.adTypeResult.value = AD_PURCHASE_TYPE
                    mPurchaseRB.setTextColor(resources?.getColor(R.color.common_white))
                    mSelloutRB.setTextColor(resources?.getColor(R.color.color_666666))
                }

                R.id.mSelloutRB -> {
                    mTradeViewModel.adTypeResult.value = AD_SELLOUT_TYPE
                    mSelloutRB.setTextColor(resources?.getColor(R.color.common_white))
                    mPurchaseRB.setTextColor(resources?.getColor(R.color.color_666666))
                }
            }

        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser)hideSoftInput()
    }
}