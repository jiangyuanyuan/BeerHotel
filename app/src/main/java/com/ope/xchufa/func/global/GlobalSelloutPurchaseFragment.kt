package com.ope.xchufa.func.global

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jakewharton.rxbinding2.view.RxView
import com.ope.base.common.BaseFragment
import com.ope.base.easy.EasyAdapter
import com.ope.base.helper.applyWidgetSchedulers
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import com.ope.xchufa.injection.component.DaggerAppComponent
import kotlinx.android.synthetic.main.fragment_global_seloutpurchase.*
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportActivity

@Route(path = RouterPath.Global.PATH_GLOBAL_SELLOUTPURCHASE)
class GlobalSelloutPurchaseFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_global_seloutpurchase

    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }
    private val mList = mutableListOf<String>()
    private val mList1 = mutableListOf<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mList.clear()
        mList1.clear()

        mList1.add("hhh")
        mList1.add("hhh")
        mList1.add("hhh")

        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")


        mSelloutRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        mSelloutRecyclerView.adapter = EasyAdapter(R.layout.item_global_sellout, {itemView,item,m->

        }, mList)

        mPurchaseRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        mPurchaseRecyclerView.adapter = EasyAdapter(R.layout.item_global_purchase, {itemView,item,m->

        }, mList)

        mEntrustRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        mEntrustRecyclerView.adapter = EasyAdapter(R.layout.item_global_entrust, {itemView,item,m->

        }, mList1)

        RxView.clicks(mEntrustNowRl)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (activity as SupportActivity).start( ARouter.getInstance().build(RouterPath.Global.PATH_GLOBAL_ENTRUST).navigation() as ISupportFragment)

                }
        mHeaderBar.onBackClick(View.OnClickListener {
            pop()
        })


    }
}