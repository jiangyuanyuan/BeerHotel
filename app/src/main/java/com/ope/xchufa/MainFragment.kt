package com.ope.xchufa

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.MenuItem
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.jakewharton.rxbinding2.view.RxView
import com.ope.base.common.BaseFragment
import com.ope.base.easy.ViewPagerAdapter
import com.ope.base.helper.applyWidgetSchedulers
import com.ope.provider.router.RouterPath
import com.ope.xchufa.injection.*
import com.ope.xchufa.injection.component.DaggerAppComponent
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.guide.*
import me.yokeyword.fragmentation.SupportFragment


@Route(path = RouterPath.Main.PATH_MAIN_FRAGMENT)
class MainFragment : BaseFragment() {
    override fun getLayoutId() = R.layout.fragment_main

    override fun injectComponent() {
        DaggerAppComponent.create().inject(this)
    }

    private val mList = mutableListOf<SupportFragment>()
    private var menuItem: MenuItem? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_trade -> {
                    mMainViewPager.currentItem = 0
                }
                R.id.item_exchange -> {
                    mMainViewPager.currentItem = 1
                }
                R.id.item_news -> {
                    mMainViewPager.currentItem = 2
                }
                R.id.item_my -> {
                    mMainViewPager.currentItem = 3
                }
            }
            false
        }

        mMainViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }
            override fun onPageSelected(position: Int) {
                if (menuItem != null) {
                    menuItem?.isChecked = false
                } else {
                    mNavigation.menu.getItem(0).isChecked = false
                }
                menuItem = mNavigation.menu.getItem(position)
                menuItem?.isChecked = true
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })

        setupViewPager(mMainViewPager)
        mNavigation.visibility = View.GONE
        RxView.clicks(mGuideOk)
                .compose(applyWidgetSchedulers())
                .subscribe {
                    mNavigation.visibility = View.VISIBLE
                    mGuide.visibility =View.GONE
                }


    }

    private fun setupViewPager(viewPager: ViewPager) {
        mList.add(mTradeFragment)
        mList.add(mExchangeFragment)
        mList.add(mNewsFragment)
        mList.add(mMyFragment)
        viewPager.adapter = ViewPagerAdapter(childFragmentManager, mList)
    }

}