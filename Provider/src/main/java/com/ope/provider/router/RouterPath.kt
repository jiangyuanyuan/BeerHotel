package com.ope.provider.router

/**
 * Created by wangshufu on 2018/3/27.
 *
 * 注意:不同的模块间,前面的地址要不同
 *
 */
object RouterPath {

    class Main{
        companion object {
            const val PATH_WELCOME = "/Main/Welcome"
            const val PATH_CLAUSE = "/Main/Clause"
            const val PATH_MAIN = "/Main/Main"
            const val PATH_MAIN_FRAGMENT = "/Main/MainFragment"
        }
    }
    class Web{
        companion object {
            const val PATH_WEB = "/Web/Web"
        }
    }


    class Trade{
        companion object {
            const val PATH_TRADE = "/Trade/Trade"
            const val PATH_TRADEHISTORY = "/Trade/TradeHistory"
            const val PATH_PURCHASE = "/Trade/Purchase"
            const val PATH_PURCHASEDETAIL = "/Trade/PurchaseDetail"
            const val PATH_SELLOUT = "/Trade/Sellout"
            const val PATH_SELLOUTDETAIL = "/Trade/SelloutDetail"
        }
    }

    class News{
        companion object {
            const val PATH_NEWS = "/News/News"
        }
    }


    class Exchange{
        companion object {
            const val PATH_EXCHANGE = "/Exchange/Exchange"
        }
    }


    class Global{
        companion object {
            const val PATH_GLOBAL = "/Global/Global"
            const val PATH_GLOBAL_SELLOUTPURCHASE = "/Global/GlobalSelloutPurchase"
            const val PATH_GLOBAL_ENTRUST = "/Global/GlobalEntrust"
            const val PATH_GLOBAL_ENTRUST_DETAIL = "/Global/GlobalEntrustDetail"
            const val PATH_GLOBAL_LEGALASSETS = "/Global/AssetsLegalFragment"
            const val PATH_GLOBAL_RECHARGE = "/Global/AssetsRechargeFragment"
            const val PATH_GLOBAL_EXTRACT = "/Global/AssetsExtractFragment"
            const val PATH_GLOBAL_PRESENTATION = "/Global/AssetsPresentationFragment"
        }
    }

    //我的
    class MyCenter{
        companion object {
            const val PATH_My = "/MyCenter/My"
            const val PATH_LOGIN = "/MyCenter/Login"
            const val PATH_LOGINFRAGMENT = "/MyCenter/LoginFragment"
            const val PATH_ABOUT = "/MyCenter/About"
            const val PATH_FEEDBACK = "/MyCenter/Feedback"
            const val PATH_UPDATE = "/MyCenter/Update"
            const val PATH_REGISTER = "/MyCenter/Register"
            const val PATH_RETRIEVEPWD = "/MyCenter/RetrievePwd"
            const val PATH_RESET = "/MyCenter/Reset"
            const val PATH_RESETLOGINPWD = "/MyCenter/ResetLoginPwd"
            const val PATH_RESETASSETSPWD = "/MyCenter/ResetAssetsPwd"
            const val PATH_AGREEMENT = "/MyCenter/Agreement"
            const val PATH_DIGITALASSETS = "/MyCenter/DigitalAssets"
            const val PATH_SECURITY = "/MyCenter/Security"

            const val PATH_KYC = "/MyCenter/Kyc"
            const val PATH_KYC_REALNAME = "/MyCenter/KycRealName"
            const val PATH_KYC_CERTIFICATES = "/MyCenter/KycCertificates"
            const val PATH_KYC_CERTIFICATES_AUSTRALIA = "/MyCenter/KycCertificatesAustralia"
            const val PATH_KYC_CERTIFICATES_VIDEO = "/MyCenter/KycVideo"


            const val PATH_PAYMENT = "/MyCenter/Payment"
            const val PATH_PAYMENT_ADDALIWECHAT = "/MyCenter/PaymentAddAliWechat"
            const val PATH_PAYMENT_ADDBANK = "/MyCenter/PaymentAddBank"
        }
    }

}