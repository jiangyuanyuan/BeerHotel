package com.ope.xchufa.func.global

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ope.base.common.BaseFragment
import com.ope.base.easy.EasyAdapter
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import com.ope.xchufa.injection.component.DaggerAppComponent
import com.ope.xchufa.injection.mGlobalSelloutPurchaseFragment
import kotlinx.android.synthetic.main.fragment_global.*
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportActivity


@Route(path = RouterPath.Global.PATH_GLOBAL)
class GlobalFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_global

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


        mRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        mRecyclerView.adapter = EasyAdapter(R.layout.item_global, {itemView,item,m->
                        itemView.setOnClickListener {
//                (activity as SupportActivity).start( mGlobalSelloutPurchaseFragment)
                            (activity as SupportActivity).start( ARouter.getInstance().build(RouterPath.Global.PATH_GLOBAL_SELLOUTPURCHASE).navigation() as ISupportFragment)

                        }
        }, mList)
    }
}