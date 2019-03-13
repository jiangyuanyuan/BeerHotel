package com.ope.xchufa.data.vm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.ope.base.KEY_TOKEN
import com.ope.base.KEY_USER_INFO
import com.ope.base.data.dto.Req
import com.ope.base.helper.DateUtils

import com.ope.base.helper.SingleLiveEvent
import com.ope.base.helper.ValidatorUtil
import com.ope.base.helper.loge
import com.ope.base.security.AESUtils
import com.ope.base.security.Base64
import com.ope.base.security.MD5Utils
import com.ope.xchufa.Country
import com.ope.xchufa.data.dto.*

import com.ope.xchufa.data.repository.TradeRepository
import com.ope.xchufa.data.repository.UserRepository
import com.ope.xchufa.ext.getToken
import com.orhanobut.hawk.Hawk
import javax.inject.Inject

class TradeViewModel @Inject constructor(private val tradeRepository: TradeRepository) : ViewModel() {

    val assetsResult = SingleLiveEvent<List<Assets>>()
    val adResult = SingleLiveEvent<AD>()
    val adTypeResult = SingleLiveEvent<String>()


    val purchaseItemResult = SingleLiveEvent<Content>()
    val selloutItemResult = SingleLiveEvent<Content>()

    val orderResult = SingleLiveEvent<Order>()



    fun getAssetsList(): LiveData<List<Assets>>{
        tradeRepository.getAssetsList().subscribe({
            if (it.isSuccessful()){
                assetsResult.value = it?.data
            }else {
                assetsResult.value = null
            }
        },{
            it.printStackTrace()
            assetsResult.value = null
        })
        return assetsResult
    }


    fun getAdOrderList(assetId:String,orderKind:String): LiveData<AD>{
        tradeRepository.getAdOrderList(assetId,orderKind).subscribe({
            if (it.isSuccessful()){
                adResult.value = it.data
            }else {
                adResult.value = null
            }
        },{
            it.printStackTrace()
            adResult.value = null
        })
        return adResult
    }

    fun order(adOrderId:String,amount:String,payway:String,assetId:String): LiveData<Order>{
        tradeRepository.order(adOrderId,amount,payway,assetId).subscribe({
            if (it.isSuccessful()){
                orderResult.value = it.data
            }else {
                orderResult.value = null
            }
        },{
            it.printStackTrace()
            orderResult.value = null
        })
        return orderResult
    }

}