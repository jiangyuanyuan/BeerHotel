package com.ope.xchufa.my.update

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ope.base.common.BaseBackFragment
import com.ope.base.data.ViewModelFactory
import com.ope.xchufa.R
import com.ope.provider.router.RouterPath
import com.ope.xchufa.injection.component.DaggerAppComponent

import kotlinx.android.synthetic.main.fragment_feedback.*
import javax.inject.Inject

@Route(path = RouterPath.MyCenter.PATH_UPDATE)
class UpdateFragment : BaseBackFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
//    private lateinit var viewModel: UploadViewModel

    override fun injectComponent() {
//        DaggerAppComponent.create().inject(this)
    }

    override fun getLayoutId() = R.layout.fragment_update


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProviders.of(_mActivity, viewModelFactory).get(UploadViewModel::class.java)
//        mHeaderBar.onBackClick(View.OnClickListener {
//            pop()
//        })
//        mSubmitBtn.enable(mContentEdt,{isBtnEnable()})
//        RxView.clicks(mSubmitBtn)
//                .compose(applyWidgetSchedulers())
//                .subscribe {
//                    (_mActivity as BaseActivity).mLoadingDialog.showLoading()
//                    viewModel.uploadFeedback(mContentEdt.text.toString().trim())
//                            .observe(this, Observer {
//                                (_mActivity as BaseActivity).mLoadingDialog.hideLoading()
//                                if (it != null) {
//                                    pop()
//                                } else {
//                                    _mActivity.toast("提交失败")
//                                }
//                            })
//                }

    }

    private fun isBtnEnable() = mContentEdt.text.isNullOrEmpty().not()

}