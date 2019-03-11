package com.ope.xchufa.func.my.assets

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.jakewharton.rxbinding2.view.RxView
import com.ope.base.common.BaseFragment
import com.ope.base.easy.EasyAdapter
import com.ope.base.helper.applyWidgetSchedulers
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import com.ope.xchufa.injection.component.DaggerAppComponent
import com.ope.xchufa.injection.mAssetsPresentationFragment
import com.ope.xchufa.injection.mAssetsRechargeFragment
import kotlinx.android.synthetic.main.fragment_global_legalassets.*
import kotlinx.android.synthetic.main.item_global_legalassets.view.*
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportFragment


@Route(path = RouterPath.Global.PATH_GLOBAL_LEGALASSETS)
class AssetsLegalFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_global_legalassets
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

        mRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        mRecyclerView.adapter = EasyAdapter(R.layout.item_global_legalassets, {itemView,item,m->
            RxView.clicks(itemView.mPresentation)
                    .compose(applyWidgetSchedulers())
                    .subscribe {
                        (activity as SupportActivity).start(mAssetsPresentationFragment as ISupportFragment)
                    }
            RxView.clicks(itemView.mRecharge)
                    .compose(applyWidgetSchedulers())
                    .subscribe {
                        (activity as SupportActivity).start(mAssetsRechargeFragment as ISupportFragment)
                    }

        }, mList)

        mHeaderBar.onBackClick(View.OnClickListener {
            pop()
        })
    }
}