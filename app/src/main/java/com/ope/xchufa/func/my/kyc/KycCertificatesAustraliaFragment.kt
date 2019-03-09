package com.ope.xchufa.func.my.kyc

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ope.base.common.BaseFragment
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import com.ope.xchufa.injection.component.DaggerAppComponent
import kotlinx.android.synthetic.main.fragment_kyc_certificates_australia.*
import android.widget.ArrayAdapter

@Route(path = RouterPath.MyCenter.PATH_KYC_CERTIFICATES_AUSTRALIA)
class KycCertificatesAustraliaFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_kyc_certificates_australia
    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHeaderBar.onBackClick(View.OnClickListener {
            pop()
        })
        val adapter = ArrayAdapter<String>(activity, R.layout.item_kyc_australia, arrayListOf("护照","水电单或信用卡账单（近三个月）","驾照"))
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mSpinner.adapter = adapter
    }
}