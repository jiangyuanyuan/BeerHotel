package com.ope.xchufa.func.trade

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.ope.base.common.BaseFragment
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import com.ope.xchufa.injection.component.DaggerAppComponent
import kotlinx.android.synthetic.main.fragment_sellout.*

import android.view.LayoutInflater

import android.app.AlertDialog
import android.arch.lifecycle.ViewModelProviders
import com.jakewharton.rxbinding2.view.RxView
import com.ope.base.helper.applyWidgetSchedulers
import com.ope.base.helper.bottomShow
import com.ope.xchufa.data.vm.TradeViewModel


@Route(path = RouterPath.Trade.PATH_SELLOUT)
class SelloutFragment : BaseFragment(){
    override fun getLayoutId() = R.layout.fragment_sellout

    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }
    private lateinit var mTradeViewModel: TradeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mTradeViewModel = ViewModelProviders.of(_mActivity, viewModelFactory).get(TradeViewModel::class.java)


        mHeaderBar.onBackClick(View.OnClickListener {
            pop()
        })

        val myView = LayoutInflater.from(activity).inflate(R.layout.dialog_sellout, null)
        val dialog = AlertDialog.Builder(activity)
                .setView(myView).create()

        RxView.clicks(mSelloutNow)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    dialog.bottomShow()
                }

    }
}