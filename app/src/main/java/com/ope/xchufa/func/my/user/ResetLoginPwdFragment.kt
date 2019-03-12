package com.ope.xchufa.func.my.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jakewharton.rxbinding2.view.RxView
import com.ope.base.KEY_USER_INFO
import com.ope.base.common.BaseFragment
import com.ope.base.helper.ValidatorUtil
import com.ope.base.helper.applyWidgetSchedulers
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import com.ope.xchufa.data.dto.UserInfo
import com.ope.xchufa.data.vm.UserViewModel
import com.ope.xchufa.injection.component.DaggerAppComponent
import com.ope.xchufa.injection.mResetAssetsPwdFragment
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_reset_loginpwd.*

import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportFragment
import org.jetbrains.anko.toast

@Route(path = RouterPath.MyCenter.PATH_RESETLOGINPWD)
class ResetLoginPwdFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_reset_loginpwd
    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }
    private val mLoginFragment: SupportFragment by lazy {
        ARouter.getInstance().build(RouterPath.MyCenter.PATH_LOGINFRAGMENT)
                .navigation() as SupportFragment
    }

    private lateinit var mUserviewModel: UserViewModel
    var userName :String?=null
    var mobile :String?=null
    var shortPhone :String?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mUserviewModel = ViewModelProviders.of(this@ResetLoginPwdFragment, viewModelFactory).get(UserViewModel::class.java)
        userName = Hawk.get<UserInfo>(KEY_USER_INFO)?.account?.userName
        mobile = Hawk.get<UserInfo>(KEY_USER_INFO)?.account?.mobile
        shortPhone = Hawk.get<UserInfo>(KEY_USER_INFO)?.account?.shortPhone
        if (userName!=null) mUsername.text = "账号："+userName

        mHeaderBar.onBackClick(View.OnClickListener {
            pop()
        })

        RxView.clicks(mSubmit)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    resetPwd()

                }

    }

    private fun resetPwd() {
        if (check()&&mobile!=null&&shortPhone!=null){
            mUserviewModel.resetPwd(mobile!!,mPassword?.text?.toString()!!,shortPhone!!).observe(this@ResetLoginPwdFragment, Observer {
                (activity as SupportActivity).startWithPop(mLoginFragment)

            })
        }
    }

    private fun check() :Boolean{
        if (!ValidatorUtil.isPassword(mPassword.text.toString())) {
            _mActivity.toast("新密码含有非法字符")
            return false
        }else if (!ValidatorUtil.isPassword(mPasswordAgain.text.toString())) {
            _mActivity.toast("确认密码含有非法字符")
            return false
        }else if (mPassword?.text?.toString()?.equals(mPasswordAgain?.text?.toString()) != true) {
            _mActivity.toast("确认密码不一致")
            return false
        }
        return true
    }
}


