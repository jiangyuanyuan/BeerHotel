package com.ope.xchufa.func.my.kyc

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jakewharton.rxbinding2.view.RxView
import com.ope.base.common.BaseFragment
import com.ope.base.easy.EasyAdapter
import com.ope.base.helper.applyWidgetSchedulers
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import com.ope.xchufa.injection.component.DaggerAppComponent
import com.ope.xchufa.injection.mKycCertificatesAustraliaFragment

import com.ope.xchufa.injection.mKycCertificatesFragment
import com.ope.xchufa.injection.mKycFragment
import com.ope.xchufa.injection.mKycRealNameFragment
import kotlinx.android.synthetic.main.fragment_kyc.*

import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportFragment


@Route(path = RouterPath.MyCenter.PATH_KYC)
class KycFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_kyc
    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHeaderBar.onBackClick(View.OnClickListener {
            pop()
        })

        RxView.clicks(mKyc1Rl)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (activity as SupportActivity).start(mKycRealNameFragment as ISupportFragment)
                }

        RxView.clicks(mKyc2Rl)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (activity as SupportActivity).start(mKycCertificatesFragment as ISupportFragment)
                }

        RxView.clicks(mKyc3Rl)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (activity as SupportActivity).start(mKycCertificatesAustraliaFragment as ISupportFragment)
                }


    }
}