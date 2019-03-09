package com.ope.xchufa.func.my.payment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route

import com.ope.base.common.BaseFragment

import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import com.ope.xchufa.injection.component.DaggerAppComponent

import kotlinx.android.synthetic.main.fragment_payment_addaliwechat.*


@Route(path = RouterPath.MyCenter.PATH_PAYMENT_ADDBANK)
class PaymentAddBank : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_payment_addaliwechat
    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHeaderBar.onBackClick(View.OnClickListener {
            pop()
        })


//        RxView.clicks(mKyc1Rl)
//                .compose(applyWidgetSchedulers())
//                .subscribe {
//                    (activity as SupportActivity).start(mKycRealNameFragment as ISupportFragment)
//                }
//


    }
}