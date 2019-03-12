package com.ope.xchufa.func.my.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jakewharton.rxbinding2.view.RxView
import com.ope.base.common.BaseFragment
import com.ope.base.helper.ValidatorUtil
import com.ope.base.helper.applyWidgetSchedulers
import com.ope.base.helper.getPhoneNumber
import com.ope.base.helper.loge
import com.ope.base.view.WheelPickerDialog
import com.ope.provider.router.RouterPath
import com.ope.xchufa.Country
import com.ope.xchufa.R
import com.ope.xchufa.data.vm.UserViewModel
import com.ope.xchufa.injection.component.DaggerAppComponent




import kotlinx.android.synthetic.main.fragment_register.*
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportFragment
import org.jetbrains.anko.toast


@Route(path = RouterPath.MyCenter.PATH_REGISTER)
class RegisterFragment : BaseFragment() {
    override fun getLayoutId() = R.layout.fragment_register

    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }

    private val mLoginFragment: SupportFragment by lazy {
        ARouter.getInstance().build(RouterPath.MyCenter.PATH_LOGINFRAGMENT)
                .navigation() as SupportFragment
    }

    private val mAgreementFragment: SupportFragment by lazy {
        ARouter.getInstance().build(RouterPath.MyCenter.PATH_AGREEMENT)
                .navigation() as SupportFragment
    }


    private lateinit var mUserviewModel: UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserviewModel = ViewModelProviders.of(this@RegisterFragment, viewModelFactory).get(UserViewModel::class.java)
        mHeaderBar.onBackClick(View.OnClickListener {
            pop()
        })

        RxView.clicks(mRegister)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    register()
                }

        RxView.clicks(mRegisterOkAgreement)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (activity as SupportActivity).start(mAgreementFragment)
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

    private fun register() {
        if (check()) {
            mUserviewModel.rigster(mUsername.text.toString(),mPhoneEt.text.toString(),mPassword.text.toString(),mPhoneRegion.text.toString()).observe(_mActivity, Observer {
                _mActivity.toast("注册成功")
                (activity as SupportActivity).startWithPop(mLoginFragment)
            })
        }
    }

    private fun check(): Boolean {
        if (!ValidatorUtil.isUsername(mUsername.text.toString())) {
            _mActivity.toast("账户名含有非法字符,6到20位字符数字组合")
            return false
        } else if (!ValidatorUtil.isPassword(mPassword.text.toString())) {
            _mActivity.toast("密码含有非法字符")
            return false
        } else if (!ValidatorUtil.isPassword(mPasswordAgain.text.toString())) {
            _mActivity.toast("确认密码含有非法字符")
            return false
        } else if (!ValidatorUtil.isMobile(mPhoneEt.text.toString())) {
            _mActivity.toast("手机号含有非法字符")
            return false
        }
//        else if (!ValidatorUtil.isVerificationCode(mVerificationCode.text.toString())) {
//            _mActivity.toast("验证码含有非法字符")
//            return false
//        }
        else if (mPassword?.text?.toString()?.equals(mPasswordAgain?.text?.toString()) != true) {
            _mActivity.toast("确认密码不一致")
            return false
        } else if (!mRegisterOkImg.isChecked) {
            _mActivity.toast("请仔细阅读《mRegisterOkAgreement》,并同意")
            return false
        }
//        else if (mUserviewModel.verificationCodeResult.value == null || mVerificationCode.text.toString() != mUserviewModel.verificationCodeResult.value ){
//            _mActivity.toast("验证码有误")
//            return false
//        }
        return true
    }
}