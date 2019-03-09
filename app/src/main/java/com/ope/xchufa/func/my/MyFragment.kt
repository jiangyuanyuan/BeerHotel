package com.ope.xchufa.func.my

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ope.base.common.BaseFragment
import com.ope.base.helper.applyWidgetSchedulers
import com.ope.xchufa.R
import com.ope.provider.common.ProviderConstant
import com.ope.provider.router.RouterPath

import com.jakewharton.rxbinding2.view.RxView
import com.ope.xchufa.injection.component.DaggerAppComponent
import com.ope.xchufa.injection.mDigitalAssetsFragment
import com.ope.xchufa.injection.mKycFragment
import com.ope.xchufa.injection.mPaymentFragment
import com.ope.xchufa.injection.mSecurityFragment
import com.orhanobut.hawk.Hawk
import com.tencent.bugly.crashreport.CrashReport
import kotlinx.android.synthetic.main.fragment_my.*
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportFragment
import org.jetbrains.anko.toast
import javax.inject.Inject

@Route(path = RouterPath.MyCenter.PATH_My)
class MyFragment : BaseFragment() {
    override fun getLayoutId() = R.layout.fragment_my

    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }
//    @Inject
//    lateinit var viewModelFactory: ViewModelFactory
//    private lateinit var viewModel: UserViewModel
//    private lateinit var manageViewModel: ManageViewModel




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProviders.of(_mActivity, viewModelFactory).get(UserViewModel::class.java)
//        manageViewModel = ViewModelProviders.of(_mActivity, viewModelFactory).get(ManageViewModel::class.java)

        RxView.clicks(mDigitalAssetsRl)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (parentFragment as SupportFragment).start(mDigitalAssetsFragment as ISupportFragment)
                }

        RxView.clicks(mSecuritySettingRl)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (parentFragment as SupportFragment).start(mSecurityFragment as ISupportFragment)
                }

        RxView.clicks(mAuthenticationRl)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (parentFragment as SupportFragment).start(mKycFragment as ISupportFragment)
                }


        RxView.clicks(mReceivablesRl)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (parentFragment as SupportFragment).start(mPaymentFragment as ISupportFragment)
                }



    }

    override fun onSupportVisible() {
        super.onSupportVisible()
//        manageViewModel.memberList()
//        viewModel.getUserInfo().name?.apply {
//            mInfoMiv.setText(this)
//        }
    }


}