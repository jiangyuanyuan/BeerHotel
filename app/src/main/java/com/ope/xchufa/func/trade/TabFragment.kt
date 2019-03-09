package com.ope.xchufa.func.trade

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

class TabFragment :Fragment(){

    private val mList = mutableListOf<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tab,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mList.clear()
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")

        mRecyclerView.layoutManager =LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        mRecyclerView.adapter = EasyAdapter(R.layout.item_trade_tab, {itemView,item,m->
            itemView.mPurchase.setOnClickListener {
                (activity as SupportActivity).start( ARouter.getInstance().build(RouterPath.Trade.PATH_SELLOUT).navigation() as ISupportFragment)
            }
        }, mList)
    }
}