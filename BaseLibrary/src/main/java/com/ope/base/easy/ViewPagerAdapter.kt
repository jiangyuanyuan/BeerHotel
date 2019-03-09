package com.ope.base.easy

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

open class ViewPagerAdapter(fm: FragmentManager, private val marrayList: List<Fragment>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return marrayList[position]
    }

    override fun getCount(): Int {
        return marrayList.size
    }
}