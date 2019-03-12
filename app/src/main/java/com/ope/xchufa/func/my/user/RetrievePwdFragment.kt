package com.ope.xchufa.func.my.user

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jakewharton.rxbinding2.view.RxView
import com.ope.base.common.BaseFragment
import com.ope.base.helper.ValidatorUtil
import com.ope.base.helper.applyWidgetSchedulers
import com.ope.base.view.WheelPickerDialog
import com.ope.provider.router.RouterPath
import com.ope.xchufa.Country
import com.ope.xchufa.R
import com.ope.xchufa.data.vm.UserViewModel
import com.ope.xchufa.injection.component.DaggerAppComponent

import kotlinx.android.synthetic.main.fragment_retrievepwd.*
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportFragment
import org.jetbrains.anko.toast

@Route(path = RouterPath.MyCenter.PATH_RETRIEVEPWD)
class RetrievePwdFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_retrievepwd

    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }
    private val mResetLoginPwdFragment: SupportFragment by lazy {
        ARouter.getInstance().build(RouterPath.MyCenter.PATH_RESETLOGINPWD)
                .navigation() as SupportFragment
    }

    private lateinit var mUserviewModel: UserViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserviewModel = ViewModelProviders.of(this@RetrievePwdFragment, viewModelFactory).get(UserViewModel::class.java)
        mHeaderBar.onBackClick(View.OnClickListener {
            pop()
        })

        RxView.clicks(mApply)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    check()
                }
        RxView.clicks(mGetVerificationCode)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    getVerificationCode()
                }


        RxView.clicks(mCountryRl)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    WheelPickerDialog.Builder(activity!!).no("取消").yes("确定").message(Country.mCountryList).build()
                            .setNoListener { it.dismiss() }.setYesListener { dialog, message ->
                                dialog.dismiss()
                                mCountry.text = message
                                when (message) {
                                    Country.mCountryList[0] -> mPhoneRegion.text = Country.AUSTRALIA_PHONETYPE
                                    Country.mCountryList[1] -> mPhoneRegion.text = Country.CHINA_PHONETYPE
                                }
                            }.show()
                }


    }

    private fun getVerificationCode() {
        if (!ValidatorUtil.isMobile(mPhoneEt.text.toString())) {
            _mActivity.toast("手机号含有非法字符")
            return
        }
        if (mPhoneEt.text.toString().isNotBlank()){
            mUserviewModel.getVerificationCode(mPhoneEt.text.toString(), mPhoneRegion.text.toString())
            _mActivity.toast("验证码已发送")
        }
    }


    private fun check() {
        if (mUserviewModel.verificationCodeResult.value == null || mVerificationCode.text.toString() != mUserviewModel.verificationCodeResult.value ){
            _mActivity.toast("验证码有误")
        }else {
//            (activity as SupportActivity).startWithPop(mResetLoginPwdFragment)
        }
        (activity as SupportActivity).startWithPop(mResetLoginPwdFragment)

    }
}