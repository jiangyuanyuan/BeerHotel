package com.ope.xchufa.func.trade

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ope.base.common.BaseFragment
import com.ope.base.easy.EasyAdapter
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import com.ope.xchufa.injection.component.DaggerAppComponent
import kotlinx.android.synthetic.main.fragment_trade_history.*

@Route(path = RouterPath.Trade.PATH_TRADEHISTORY)
class TradeHistoryFragment : BaseFragment(){
    override fun getLayoutId()= R.layout.fragment_trade_history

    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }

    private val mList = mutableListOf<String>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mList.clear()
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")

        mRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        mRecyclerView.adapter = EasyAdapter(R.layout.item_trade_history, {itemView,item,m->

        }, mList)

        mHeaderBar.onBackClick(View.OnClickListener {
            pop()
        })

    }

}