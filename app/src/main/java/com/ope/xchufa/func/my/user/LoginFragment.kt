package com.ope.xchufa.func.my.user

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jakewharton.rxbinding2.view.RxView
import com.ope.base.KEY_TOKEN
import com.ope.base.common.BaseFragment
import com.ope.base.helper.applyWidgetSchedulers
import com.ope.base.view.WheelPickerDialog
import com.ope.provider.router.RouterPath
import com.ope.xchufa.Country
import com.ope.xchufa.Country.Companion.COUNTRYTYPE
import com.ope.xchufa.R
import com.ope.xchufa.data.vm.UserViewModel
import com.ope.xchufa.injection.component.DaggerAppComponent
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_login.*
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportFragment
import org.jetbrains.anko.toast



@Route(path = RouterPath.MyCenter.PATH_LOGINFRAGMENT)
class LoginFragment : BaseFragment() {
    override fun getLayoutId() = R.layout.fragment_login
    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }
    private lateinit var mUserviewModel: UserViewModel

    private val mRegisterFragment: SupportFragment by lazy {
        ARouter.getInstance().build(RouterPath.MyCenter.PATH_REGISTER)
                .navigation() as SupportFragment
    }
    private val mRetrievePwdFragment: SupportFragment by lazy {
        ARouter.getInstance().build(RouterPath.MyCenter.PATH_RETRIEVEPWD)
                .navigation() as SupportFragment
    }

    private val mMainFragment: SupportFragment by lazy {
        ARouter.getInstance().build(RouterPath.Main.PATH_MAIN_FRAGMENT)
                .navigation() as SupportFragment
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mUserviewModel = ViewModelProviders.of(_mActivity,viewModelFactory).get(UserViewModel::class.java)
        Hawk.delete(KEY_TOKEN)
        mCountry.text = when(Hawk.get<String>(Country.COUNTRYTYPE)){
            Country.AUSTRALIA_PHONETYPE->Country.mCountryList[0]
            Country.CHINA_PHONETYPE->Country.mCountryList[1]
            else ->Country.mCountryList[1]
        }

        RxView.clicks(mLogin)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    login()
                }



        RxView.clicks(mRigist)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (activity as SupportActivity).start(mRegisterFragment)
                }

        RxView.clicks(mRetrievePwd)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (activity as SupportActivity).start(mRetrievePwdFragment)
                }

        RxView.clicks(mCountryRl)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    WheelPickerDialog.Builder(activity!!).no("取消").yes("确定").message(Country.mCountryList).build()
                            .setNoListener { it.dismiss() }.setYesListener { dialog, message ->
                                mCountry.text = message
                                mUserviewModel.countryType.value = message
                                dialog.dismiss()
                            }.show()


                }


    }

    private fun login() {
        mUserviewModel.login(mUsername.text.toString(),mPassword.text.toString(),"86").observe(this, Observer {
            if (it == null){
                _mActivity.toast("登录失败")
            }else{
                when(mCountry.text){
                    Country.mCountryList[0]->Hawk.put(Country.COUNTRYTYPE,Country.AUSTRALIA_PHONETYPE)
                    Country.mCountryList[1]->Hawk.put(Country.COUNTRYTYPE,Country.CHINA_PHONETYPE)
                }
                (activity as SupportActivity).startWithPop(mMainFragment)

            }
        })
    }
}