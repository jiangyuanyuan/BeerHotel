package com.ope.xchufa.func.news

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
import kotlinx.android.synthetic.main.fragment_tab.*
import kotlinx.android.synthetic.main.item_trade_tab.view.*
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportActivity

@Route(path = RouterPath.News.PATH_NEWS)
class NewsFragment : BaseFragment(){
    override fun getLayoutId()= R.layout.fragment_news

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
        mRecyclerView.adapter = EasyAdapter(R.layout.item_news, {itemView,item,m->
        }, mList)
    }
}