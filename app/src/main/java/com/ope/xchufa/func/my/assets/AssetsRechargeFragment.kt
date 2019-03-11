package com.ope.xchufa.func.my.assets

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ope.base.common.BaseFragment
import com.ope.base.easy.EasyAdapter
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import com.ope.xchufa.injection.component.DaggerAppComponent
import kotlinx.android.synthetic.main.fragment_digtakassets.*


@Route(path = RouterPath.Global.PATH_GLOBAL_RECHARGE)
class AssetsRechargeFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_global_assets_recharge
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

//        mRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
//        mRecyclerView.adapter = EasyAdapter(R.layout.item_digtakassets, {itemView,item,m->
//
//        }, mList)
//
        mHeaderBar.onBackClick(View.OnClickListener {
            pop()
        })
    }
}