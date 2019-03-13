package com.ope.xchufa



class Country{
    companion object {
        val mCountryList = arrayListOf("Australia（澳大利亚）", "China（中国）")
        val COUNTRYTYPE = "PHONETYPE"
        val AUSTRALIA_PHONETYPE = "+61"
        val CHINA_PHONETYPE = "+81"
    }

}


const val  GUIDE = "GUIDE"
const val  AD_PURCHASE_TYPE = "1"//默认买入广告
const val  AD_SELLOUT_TYPE = "0"//默认卖出广告



class PayType{
    companion object {
        val PAY_TYPE_ALI = 1
        val PAY_TYPE_WECHAT = 2
        val PAY_TYPE_BANK = 3
    }
}



