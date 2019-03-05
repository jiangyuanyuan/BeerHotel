package com.ope.xchufa.my.about

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ope.base.common.BaseBackFragment
import com.ope.base.helper.applyWidgetSchedulers
import com.ope.xchufa.R
import com.ope.provider.router.RouterPath

import com.jakewharton.rxbinding2.view.RxView
import com.ope.xchufa.ext.getVersionCode
import kotlinx.android.synthetic.main.fragment_about.*


import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportFragment

@Route(path = RouterPath.MyCenter.PATH_ABOUT)
class AboutFragment : BaseBackFragment() {
    val TAG = "AboutFragment"

    private val feedbackFragment: SupportFragment by lazy {
        ARouter.getInstance().build(RouterPath.MyCenter.PATH_FEEDBACK)
                .navigation() as SupportFragment
    }

    private val updateFragment: SupportFragment by lazy {
        ARouter.getInstance().build(RouterPath.MyCenter.PATH_UPDATE)
                .navigation() as SupportFragment
    }

    override fun injectComponent() {}

    override fun getLayoutId() = R.layout.fragment_about


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHeaderBar.onBackClick(View.OnClickListener {
            pop()
        })

        RxView.clicks(mFeedbackLayout)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    start(feedbackFragment as ISupportFragment)

                }
        RxView.clicks(mVersionLayout)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    start(updateFragment as ISupportFragment)
//                    Beta.checkUpgrade()
                }
        RxView.clicks(mIntroductionLayout)
                .compose(applyWidgetSchedulers())
                .subscribe {

                }



        mVersionTv.text = "当前版本 ${context?.getVersionCode()}"

    }

}