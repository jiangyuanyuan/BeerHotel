package com.ope.xchufa.func.my.login

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jakewharton.rxbinding2.view.RxView
import com.ope.base.common.BaseFragment
import com.ope.base.helper.applyWidgetSchedulers
import com.ope.base.helper.loge
import com.ope.base.view.WheelPickerDialog
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import com.ope.xchufa.injection.component.DaggerAppComponent
import com.ope.xchufa.injection.mRegisterFragment
import com.ope.xchufa.injection.mRetrievePwdFragment
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_login.*
import me.yokeyword.fragmentation.SupportActivity
import java.util.concurrent.TimeUnit




@Route(path = RouterPath.MyCenter.PATH_LOGINFRAGMENT)
class LoginFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_login
    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RxView.clicks(mLogin)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    ARouter.getInstance().build(RouterPath.Main.PATH_MAIN).navigation()
                    Observable.timer(2000, TimeUnit.MILLISECONDS)
                            .subscribe {
                                activity?.finish()
                            }
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
                   WheelPickerDialog.Builder(activity!!).no("取消").yes("确定").build()
                            .setNoListener { it.dismiss() }.setYesListener { dialog, message ->
                                dialog.dismiss()
                               mCountry.text = message
                            }.show()

                }



    }
}