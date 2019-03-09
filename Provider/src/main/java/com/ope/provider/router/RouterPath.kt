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
            const val PATH_GLOBALSELLOUTPURCHASE = "/Global/GlobalSelloutPurchase"
        }
    }

//    //家庭成员管理模块
//    class UserManageCenter{
//        companion object {
//            const val PATH_MANAGE = "/UserManageCenter/Manage"
//            const val PATH_ADD = "/UserManageCenter/Add"
//            const val PATH_DELETE = "/UserManageCenter/Delete"
//        }
//    }



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