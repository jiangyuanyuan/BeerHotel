package com.ope.xchufa.injection

import com.alibaba.android.arouter.launcher.ARouter
import com.ope.provider.router.RouterPath
import me.yokeyword.fragmentation.SupportFragment

val mTradeFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.Trade.PATH_TRADE)
            .navigation() as SupportFragment
}

val mNewsFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.News.PATH_NEWS)
            .navigation() as SupportFragment
}

val mExchangeFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.Exchange.PATH_EXCHANGE)
            .navigation() as SupportFragment
}

val mMyFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.MyCenter.PATH_My)
            .navigation() as SupportFragment
}

 val mDigitalAssetsFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.MyCenter.PATH_DIGITALASSETS)
            .navigation() as SupportFragment
}

val mSecurityFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.MyCenter.PATH_SECURITY)
            .navigation() as SupportFragment
}

val mLoginFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.MyCenter.PATH_LOGINFRAGMENT)
            .navigation() as SupportFragment
}

val mResetAssetsPwdFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.MyCenter.PATH_RESETASSETSPWD)
            .navigation() as SupportFragment
}

val mResetLoginPwdFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.MyCenter.PATH_RESETLOGINPWD)
            .navigation() as SupportFragment
}

val mResetPwdFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.MyCenter.PATH_RESET)
            .navigation() as SupportFragment
}


val mTradeHistoryFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.Trade.PATH_TRADEHISTORY)
            .navigation() as SupportFragment
}

val mTradeDetailFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.Trade.PATH_PURCHASEDETAIL)
            .navigation() as SupportFragment
}

val mRegisterFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.MyCenter.PATH_REGISTER)
            .navigation() as SupportFragment
}

val mRetrievePwdFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.MyCenter.PATH_RETRIEVEPWD)
            .navigation() as SupportFragment
}

val mAgreementFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.MyCenter.PATH_RETRIEVEPWD)
            .navigation() as SupportFragment
}
val mKycFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.MyCenter.PATH_KYC)
            .navigation() as SupportFragment
}

val mKycRealNameFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.MyCenter.PATH_KYC_REALNAME)
            .navigation() as SupportFragment
}

val mKycCertificatesFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.MyCenter.PATH_KYC_CERTIFICATES)
            .navigation() as SupportFragment
}

val mKycCertificatesAustraliaFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.MyCenter.PATH_KYC_CERTIFICATES_AUSTRALIA)
            .navigation() as SupportFragment
}

val mPaymentFragment: SupportFragment by lazy {
    ARouter.getInstance().build(RouterPath.MyCenter.PATH_PAYMENT)
            .navigation() as SupportFragment
}







