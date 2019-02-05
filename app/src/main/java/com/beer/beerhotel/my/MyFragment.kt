package com.iwith.family.my

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.beer.baselibrary.common.BaseFragment
import com.beer.baselibrary.helper.applyWidgetSchedulers
import com.beer.beerhotel.R
import com.beer.provider.common.ProviderConstant
import com.beer.provider.router.RouterPath

import com.jakewharton.rxbinding2.view.RxView
import com.orhanobut.hawk.Hawk
import com.tencent.bugly.crashreport.CrashReport
import kotlinx.android.synthetic.main.fragment_my.*
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportFragment
import org.jetbrains.anko.toast
import javax.inject.Inject

@Route(path = RouterPath.MyCenter.PATH_My)
class MyFragment : BaseFragment() {

//    @Inject
//    lateinit var viewModelFactory: ViewModelFactory
//    private lateinit var viewModel: UserViewModel
//    private lateinit var manageViewModel: ManageViewModel

    private val logoutFragment: SupportFragment by lazy {
        ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGOUT)
                .navigation() as SupportFragment
    }
    private val userInfoFragment: SupportFragment by lazy {
        ARouter.getInstance().build(RouterPath.UserCenter.PATH_USERINFO)
                .navigation() as SupportFragment
    }
    private val userManageFragment: SupportFragment by lazy {
        ARouter.getInstance().build(RouterPath.UserManageCenter.PATH_MANAGE)
                .navigation() as SupportFragment
    }

    override fun injectComponent() {
//        DaggerAppComponent.create().inject(this)
    }

    override fun getLayoutId() = R.layout.fragment_my


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProviders.of(_mActivity, viewModelFactory).get(UserViewModel::class.java)
//        manageViewModel = ViewModelProviders.of(_mActivity, viewModelFactory).get(ManageViewModel::class.java)

//        val myPhotoFragment = ARouter.getInstance().build(RouterPath.PhotoCenter.PATH_MY)
//                .withLong(ProviderConstant.INTENT_PHOTO_UID, viewModel.getUserInfo().uid!!)
//                .navigation() as SupportFragment

        RxView.clicks(mInfoMiv)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (parentFragment as SupportFragment).start(userInfoFragment as ISupportFragment)
                }
//        RxView.clicks(mPhotoMiv)
//                .compose(applyWidgetSchedulers())
//                .subscribe {
//                    (parentFragment as SupportFragment).start(myPhotoFragment as ISupportFragment)
//                }
        RxView.clicks(mFamilyMiv)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (parentFragment as SupportFragment).start(userManageFragment as ISupportFragment)
                }
        RxView.clicks(mSettingMiv)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    (parentFragment as SupportFragment).start(logoutFragment as ISupportFragment)
                }

//        manageViewModel.confirmationLiveData.observe(this, Observer {
//            if (it == null || it == 0) {
//                mFamilyMiv.hideDot()
//            } else {
//                mFamilyMiv.showDot(it)
//            }
//        })

    }

    override fun onSupportVisible() {
        super.onSupportVisible()
//        manageViewModel.memberList()
//        viewModel.getUserInfo().name?.apply {
//            mInfoMiv.setText(this)
//        }
    }


}