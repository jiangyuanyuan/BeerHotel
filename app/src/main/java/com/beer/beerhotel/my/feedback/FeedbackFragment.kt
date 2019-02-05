package com.beer.beerhotel.my.feedback

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.beer.baselibrary.common.BaseActivity
import com.beer.baselibrary.common.BaseBackFragment
import com.beer.baselibrary.data.ViewModelFactory
import com.beer.baselibrary.helper.applyWidgetSchedulers
import com.beer.baselibrary.helper.enable
import com.beer.beerhotel.R
import com.beer.provider.router.RouterPath

import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.fragment_feedback.*
import org.jetbrains.anko.toast
import javax.inject.Inject

@Route(path = RouterPath.MyCenter.PATH_FEEDBACK)
class FeedbackFragment : BaseBackFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
//    private lateinit var viewModel: UploadViewModel

    override fun injectComponent() {
//        DaggerAppComponent.create().inject(this)
    }

    override fun getLayoutId() = R.layout.fragment_feedback


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProviders.of(_mActivity, viewModelFactory).get(UploadViewModel::class.java)
        mHeaderBar.onBackClick(View.OnClickListener {
            pop()
        })
        mSubmitBtn.enable(mContentEdt,{isBtnEnable()})
        RxView.clicks(mSubmitBtn)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (_mActivity as BaseActivity).mLoadingDialog.showLoading()
//                    viewModel.uploadFeedback(mContentEdt.text.toString().trim())
//                            .observe(this, Observer {
//                                (_mActivity as BaseActivity).mLoadingDialog.hideLoading()
//                                if (it != null) {
//                                    pop()
//                                } else {
//                                    _mActivity.toast("提交失败")
//                                }
//                            })
                }

    }

    private fun isBtnEnable() = mContentEdt.text.isNullOrEmpty().not()

}