package com.ope.xchufa.func.trade

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.jakewharton.rxbinding2.view.RxView
import com.ope.base.common.BaseFragment
import com.ope.base.helper.applyWidgetSchedulers
import com.ope.base.helper.stripZeros
import com.ope.base.view.DefaultTextWatcher
import com.ope.provider.router.RouterPath

import com.ope.xchufa.PayType
import com.ope.xchufa.R
import com.ope.xchufa.data.dto.Content
import com.ope.xchufa.data.vm.TradeViewModel
import com.ope.xchufa.injection.component.DaggerAppComponent
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_purchase.*
import me.yokeyword.fragmentation.SupportActivity
import me.yokeyword.fragmentation.SupportFragment
import org.jetbrains.anko.toast
import java.math.BigDecimal
import java.util.concurrent.TimeUnit


@Route(path = RouterPath.Trade.PATH_PURCHASE)
class PurchaseFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_purchase

    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }

    private val mTradeDetailFragment: SupportFragment by lazy {
        ARouter.getInstance().build(RouterPath.Trade.PATH_PURCHASEDETAIL)
                .navigation() as SupportFragment
    }
    private lateinit var mTradeViewModel: TradeViewModel
    private var mCountdownTime = 60
    private  var timer:Disposable? =null
    private  var mCnFocus = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mTradeViewModel = ViewModelProviders.of(_mActivity, viewModelFactory).get(TradeViewModel::class.java)

        mHeaderBar.onBackClick(View.OnClickListener {
            pop()
        })

        RxView.clicks(mPurchaseExchange60)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    pop()
                }

        RxView.clicks(mPurchaseOk)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    order()
//                    mTradeViewModel.order(mTradeViewModel?.purchaseItemResult?.value?.adOrder?.adOrderId,mChufa.text.)
//                    (activity as SupportActivity).startWithPop(mTradeDetailFragment)
                }

//        RxView.clicks(mPurchaseExchange60)
//                .compose(applyWidgetSchedulers())
//                .subscribe {
//                    OneTextDialog.Builder(activity!!).message("超过2000 CNY的订单需要高级认证").no("知道了").build()
//                            .setNoListener { it.dismiss() }.show()
//                }

        mCNY.addTextChangedListener(object :DefaultTextWatcher(){
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                super.onTextChanged(s, start, before, count)
                if (mCnFocus){
                    var text =mCNY?.text?.toString()?:"0"
                    if (text.isBlank())text = "0"
                    var cn =BigDecimal(text)
                    var priceRate = mTradeViewModel?.purchaseItemResult?.value?.adOrder?.price?.multiply(BigDecimal("1.005"))
                    var total =cn.divide(priceRate,4, BigDecimal.ROUND_HALF_EVEN).stripZeros()
                    if (total!=null) mChufa.setText(total.toCharArray(),0,total.length)

                }
            }
        })
        mCNY.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            mCnFocus = hasFocus
        }
        mChufa.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            mCnFocus = !hasFocus
        }
        mChufa.addTextChangedListener(object :DefaultTextWatcher(){
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                super.onTextChanged(s, start, before, count)
                if (!mCnFocus){
                    var text =mChufa?.text?.toString()?:"0"
                    if (text.isBlank())text = "0"
                    var cn =BigDecimal(text)
                    var priceRate = mTradeViewModel?.purchaseItemResult?.value?.adOrder?.price?.multiply(BigDecimal("1.005"))
                    var total =cn.multiply(priceRate).stripZeros()
                    if (total!=null) mCNY.setText(total.toCharArray(),0,total.length)
                }

            }
        })


        mCountdownTime = 60
        countdownTime()
        mTradeViewModel.purchaseItemResult.observe(_mActivity, Observer {
            setData(mTradeViewModel.purchaseItemResult.value)
        })
        setData(mTradeViewModel.purchaseItemResult.value)
    }

    private fun order() {
        if (check()){
            var adOrderId = mTradeViewModel.purchaseItemResult.value?.adOrder?.adOrderId?.toString()?:""
            var assetId = mTradeViewModel.purchaseItemResult.value?.adOrder?.assetId?.toString()?:""
            mTradeViewModel.order(adOrderId,mChufa?.text?.toString()!!,"1",assetId).observe(_mActivity, Observer {
                if (it!=null){
                    hideSoftInput()
                    (activity as SupportActivity).startWithPop(mTradeDetailFragment)
                }else {
                    _mActivity.toast("购买失败")
                }
            })
        }
    }

    private fun check() :Boolean{
        var max = mTradeViewModel.purchaseItemResult.value?.adOrder?.maxTxAmount
        var min = mTradeViewModel.purchaseItemResult.value?.adOrder?.minTxAmount
        if (mChufa?.text?.toString().isNullOrBlank()){
            _mActivity.toast("请输入需要购买的Chufa数量")
            return false
        }else if (BigDecimal(mChufa?.text?.toString()) > max){
            _mActivity.toast("最大购买量不超过${max.stripZeros()}")
            return false
        }else if (BigDecimal(mChufa?.text?.toString()) < min){
            _mActivity.toast("最少购买量不超过${min.stripZeros()}")
            return false
        }
        return true
    }

    override fun onPause() {
        super.onPause()
        if (timer!=null){
            timer!!.dispose()
        }
    }

    private fun countdownTime() {
        timer =Observable.timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    mCountdownTime--
                    mPurchaseExchange60.text = mCountdownTime.toString() + "取消"
                    if (mCountdownTime<=0){
                        pop()
                    }else {
                        countdownTime()
                    }
                }
    }

    private fun setData(item: Content?) {
        if (item!=null){
            mUsername?.text = item?.saleIntro?.userName ?: ""
            mStock?.text = "库存：" + item?.adOrder?.totalAmount?.stripZeros() ?: ""
            mUnitPrice?.text = "单价：" + item?.adOrder?.price?.stripZeros() + item?.adOrder?.currencyCode
            mQuota?.text = "单笔限额：" + item?.adOrder?.minTxAmount.stripZeros() + "~" + item?.adOrder?.maxTxAmount.stripZeros() + item?.adOrder?.currencyCode
            mAliPayIcon?.visibility = if (item?.payInfos.any { it.payType == PayType.PAY_TYPE_ALI }) View.VISIBLE else View.GONE
            mWeChatPayIcon?.visibility = if (item?.payInfos.any { it.payType == PayType.PAY_TYPE_WECHAT }) View.VISIBLE else View.GONE
            if (item?.payInfos.any { it.payType == PayType.PAY_TYPE_BANK }){
                mBankPayIcon?.visibility =  View.VISIBLE
                mBankTv?.text = item?.payInfos?.filter { it.payType == PayType.PAY_TYPE_BANK }[0].cardName
                mBankTv?.visibility = View.VISIBLE
            }else {
                mBankPayIcon?.visibility =  View.GONE
                mBankTv?.visibility = View.GONE
            }
        }
    }
}