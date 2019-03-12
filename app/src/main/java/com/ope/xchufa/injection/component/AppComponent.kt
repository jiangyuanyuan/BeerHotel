package com.ope.xchufa.injection.component


import com.ope.xchufa.MainActivity
import com.ope.xchufa.MainFragment
import com.ope.xchufa.func.exchange.ExchangeFragment
import com.ope.xchufa.func.global.GlobalEntrustDetailFragment
import com.ope.xchufa.func.global.GlobalEntrustFragment
import com.ope.xchufa.func.global.GlobalFragment
import com.ope.xchufa.func.global.GlobalSelloutPurchaseFragment
import com.ope.xchufa.func.my.MyFragment
import com.ope.xchufa.func.my.assets.*
import com.ope.xchufa.func.my.kyc.*
import com.ope.xchufa.func.my.user.LoginFragment
import com.ope.xchufa.func.my.payment.PaymentAddAliWechatFragment
import com.ope.xchufa.func.my.payment.PaymentAddBank
import com.ope.xchufa.func.my.payment.PaymentFragment
import com.ope.xchufa.func.my.user.*
import com.ope.xchufa.injection.module.AppModule

import com.ope.xchufa.func.news.NewsFragment
import com.ope.xchufa.func.trade.*


import javax.inject.Singleton

@Singleton
@dagger.Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: MainFragment)

    //我的
    fun inject(fragment: LoginFragment)
    fun inject(fragment: MyFragment)
    fun inject(fragment: AgreementFragment)
    fun inject(fragment: RegisterFragment)
    fun inject(fragment: ResetPwdFragment)
    fun inject(fragment: RetrievePwdFragment)
    fun inject(fragmentLogin: ResetLoginPwdFragment)
    fun inject(fragmentLogin: ResetAssetsPwdFragment)
    fun inject(fragment: AssetsDigitalFragment)
    fun inject(fragment: SecurityFragment)

    fun inject(fragment: KycFragment)
    fun inject(fragment: KycRealNameFragment)
    fun inject(fragment: KycCertificatesFragment)
    fun inject(fragment: KycCertificatesAustraliaFragment)
    fun inject(fragment: KycVideoFragment)

    fun inject(fragment: PaymentFragment)
    fun inject(fragment: PaymentAddAliWechatFragment)
    fun inject(fragment: PaymentAddBank)

    //交易
    fun inject(fragment: TradeFragment)
    fun inject(fragment: PurchaseFragment)
    fun inject(fragment: SelloutFragment)
    fun inject(fragment: PurchaseDetailFragment)
    fun inject(fragment: SelloutDetailFragment)
    fun inject(fragment: TradeHistoryFragment)

    //全球
    fun inject(fragment: GlobalFragment)
    fun inject(fragment: GlobalSelloutPurchaseFragment)
    fun inject(fragment: GlobalEntrustFragment)
    fun inject(fragment: GlobalEntrustDetailFragment)
    fun inject(fragment: AssetsLegalFragment)
    fun inject(fragment: AssetsRechargeFragment)
    fun inject(fragment: AssetsExtractFragment)
    fun inject(fragment: AssetsPresentationFragment)



    //消息
    fun inject(fragment: NewsFragment)

    //交换币
    fun inject(fragment: ExchangeFragment)


}