package com.ope.xchufa.func.my

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ope.base.common.BaseFragment
import com.ope.base.helper.applyWidgetSchedulers
import com.ope.xchufa.R

import com.ope.provider.router.RouterPath

import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.view.visibility
import com.ope.base.KEY_TOKEN
import com.ope.base.KEY_USER_INFO
import com.ope.base.helper.loge
import com.ope.xchufa.Country
import com.ope.xchufa.data.dto.UserInfo
import com.ope.xchufa.data.vm.UserViewModel
import com.ope.xchufa.ext.getToken
import com.ope.xchufa.injection.*
import com.ope.xchufa.injection.component.DaggerAppComponent
import com.orhanobut.hawk.Hawk

import kotlinx.android.synthetic.main.fragment_my.*
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportFragment
import org.jetbrains.anko.toast


@Route(path = RouterPath.MyCenter.PATH_My)
class MyFragment : BaseFragment() {
    override fun getLayoutId() = R.layout.fragment_my

    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }

    private lateinit var mUserviewModel: UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mUserviewModel = ViewModelProviders.of(this@MyFragment,viewModelFactory).get(UserViewModel::class.java)

        mVisitorlogin.text = Hawk.get<UserInfo>(KEY_USER_INFO)?.account?.userName?:""
        mLegalAssetsRl.visibility = if (Hawk.get<String>(Country.COUNTRYTYPE) == Country.AUSTRALIA_PHONETYPE) View.VISIBLE else View.GONE

        RxView.clicks(mDigitalAssetsRl)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (parentFragment as SupportFragment).start(mAssetsDigitalFragment as ISupportFragment)
                }

        RxView.clicks(mTransactionRecordRl)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (parentFragment as SupportFragment).start(mTradeHistoryFragment as ISupportFragment)
                }

        RxView.clicks(mLegalAssetsRl)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (parentFragment as SupportFragment).start(mAssetsLegalFragment as ISupportFragment)
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

        RxView.clicks(mlogout)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    loge(getToken())
                    mUserviewModel.logout().observe(this, Observer {
                        (parentFragment as SupportFragment).startWithPop(mLoginFragment)
//                        if (it!=1) _mActivity.toast("退出失败，请重试") else {
//                            (parentFragment as SupportFragment).startWithPop(mLoginFragment)
//                        }
                    })
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