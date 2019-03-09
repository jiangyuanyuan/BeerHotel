package com.ope.xchufa.func.my.kyc

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
import kotlinx.android.synthetic.main.fragment_kyc_video.*


@Route(path = RouterPath.MyCenter.PATH_KYC_CERTIFICATES_VIDEO)
class KycVideoFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_kyc_video
    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHeaderBar.onBackClick(View.OnClickListener {
            pop()
        })
    }
}