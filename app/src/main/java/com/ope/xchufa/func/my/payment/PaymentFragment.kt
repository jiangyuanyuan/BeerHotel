package com.ope.xchufa.func.my.payment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ope.base.common.BaseFragment
import com.ope.base.easy.EasyAdapter

import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import com.ope.xchufa.injection.component.DaggerAppComponent
import kotlinx.android.synthetic.main.fragment_payment.*

@Route(path = RouterPath.MyCenter.PATH_PAYMENT)
class PaymentFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_payment
    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }


    private val mList = mutableListOf<String>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHeaderBar.onBackClick(View.OnClickListener {
            pop()
        })
        mHeaderBar.setRightText("编辑")
        mHeaderBar.onRightClick(View.OnClickListener {
            if (mDelete.visibility ==View.VISIBLE){
                mDelete.visibility = View.GONE
                mHeaderBar.setRightText("编辑")
            }else{
                mDelete.visibility = View.VISIBLE
                mHeaderBar.setRightText("保存")
            }

        })

        mList.clear()
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")
        mList.add("hhh")

        mRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL,false)
        mRecyclerView.adapter = EasyAdapter(R.layout.item_payment, {itemView,item,m->
        }, mList)


//        RxView.clicks(mKyc1Rl)
//                .compose(applyWidgetSchedulers())
//                .subscribe {
//                    (activity as SupportActivity).start(mKycRealNameFragment as ISupportFragment)
//                }
//


    }
}