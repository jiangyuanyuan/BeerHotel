package com.ope.xchufa.func.trade

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ope.base.common.BaseFragment
import com.ope.base.easy.ViewPagerAdapter
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
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
    private val mTitlesList = mutableListOf<String>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mTitlesList.clear()
        mList.clear()
        mTitlesList.add("Chufa")
        mTitlesList.add("BTC")
        mTitlesList.add("ETH")
        mTitlesList.add("USDT")
        mList.add(TabFragment())
        mList.add(TabFragment())
        mList.add(TabFragment())
        mList.add(TabFragment())
        mTradeViewpager.adapter = ViewPagerAdapter(childFragmentManager,mList)

        mTabs.addTab(mTabs.newTab())
        mTabs.addTab(mTabs.newTab())
        mTabs.addTab(mTabs.newTab())
        mTabs.addTab(mTabs.newTab())

        mTabs.setupWithViewPager(mTradeViewpager)

        for (i in 0 until mTitlesList.size) {
            mTabs.getTabAt(i)?.text = mTitlesList[i]
        }
    }
}