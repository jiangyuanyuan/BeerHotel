package com.ope.xchufa.data.repository

import com.ope.base.data.dto.Rep
import com.ope.base.data.dto.Req
import com.ope.base.helper.applySchedulersOnSingle
import com.ope.base.helper.loge
import com.ope.xchufa.data.api.TradeService
import com.ope.xchufa.data.api.UserService
import com.ope.xchufa.data.dto.*

import com.ope.xchufa.ext.getToken
import io.reactivex.Single
import javax.inject.Inject

class TradeRepository @Inject constructor(private val tradeService: TradeService) {

    fun getAssetsList(): Single<Rep<List<Assets>>> {
        val req = Req()
        return tradeService.getAssetsList(req)
                .compose(applySchedulersOnSingle())
    }

    fun getAdOrderList(assetId:String,orderKind:String): Single<Rep<AD>> {
        val req = Req()
        req.putParams("assetId",assetId)
        req.putParams("orderKind",orderKind)
        return tradeService.getAdOrderList(req)
                .compose(applySchedulersOnSingle())
    }

    fun order(adOrderId:String,amount:String,payway:String,assetId:String): Single<Rep<Order>> {
        val req = Req()
        req.putParams("assetId",assetId)
        req.putParams("adOrderId",adOrderId)
        req.putParams("amount",amount)
        req.putParams("payway",payway)
        return tradeService.order(req)
                .compose(applySchedulersOnSingle())
    }

}
