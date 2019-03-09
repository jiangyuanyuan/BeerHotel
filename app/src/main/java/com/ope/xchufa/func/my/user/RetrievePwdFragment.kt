package com.ope.xchufa.func.my.user

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jakewharton.rxbinding2.view.RxView
import com.ope.base.common.BaseFragment
import com.ope.base.helper.applyWidgetSchedulers
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import com.ope.xchufa.injection.component.DaggerAppComponent
import com.ope.xchufa.injection.mResetLoginPwdFragment
import kotlinx.android.synthetic.main.fragment_retrievepwd.*
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportFragment

@Route(path = RouterPath.MyCenter.PATH_RETRIEVEPWD)
class RetrievePwdFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_retrievepwd

    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mHeaderBar.onBackClick(View.OnClickListener {
            pop()
        })

        RxView.clicks(mApply)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (activity as SupportActivity).start(mResetLoginPwdFragment)
                }

    }
}