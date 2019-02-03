package com.beer.baselibrary.view

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.beer.baselibrary.R
import kotlinx.android.synthetic.main.bottom_nvg_layout.view.*

/**
 * 改成builder模式
 */
class CustomBottomNavBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var mCurrentSelectedPosition:Int? = null
    private var mTabSelectedListener: OnTabSelectedListener? = null

    init {
        initView()
    }

    private fun initView() {
        View.inflate(context, R.layout.bottom_nvg_layout,this)
        mNavPhotoLayout.setOnClickListener {
            if (mCurrentSelectedPosition != 0){
                refreshUI(0)
                mTabSelectedListener?.onTabSelected(0)
            }
        }
        mNavHealthLayout.setOnClickListener {
            if (mCurrentSelectedPosition != 1){
                refreshUI(1)
                mTabSelectedListener?.onTabSelected(1)
            }
        }
        mNavMyLayout.setOnClickListener {
            if (mCurrentSelectedPosition != 2){
                refreshUI(2)
                mTabSelectedListener?.onTabSelected(2)
            }
        }
        setFirstSelectedPosition(0)
    }

    fun setFirstSelectedPosition(firstSelectedPosition:Int): CustomBottomNavBar {
        refreshUI(firstSelectedPosition)
        return this
    }

    fun setTabSelectedListener(tabSelectedListener: OnTabSelectedListener): CustomBottomNavBar {
        this.mTabSelectedListener = tabSelectedListener
        return this
    }

    private fun refreshUI(value :Int){
        mCurrentSelectedPosition = value
        when(value){
            0 -> {
                mNavPhotoImg.setImageResource(R.mipmap.icon_photo_selected)
                mNavHealthImg.setImageResource(R.mipmap.icon_health_normal)
                mNavMyImg.setImageResource(R.mipmap.icon_mine_normal)
                mNavPhotoTv.setTextColor(ContextCompat.getColor(context, R.color.nav_text_selected))
                mNavHealthTv.setTextColor(ContextCompat.getColor(context, R.color.nav_text_normal))
                mNavMyTv.setTextColor(ContextCompat.getColor(context, R.color.nav_text_normal))
                mNavPhotoLayout.setBackgroundResource(R.drawable.bottom_nvg_selected_rectangle)
                mNavHealthLayout.setBackgroundResource(R.color.color_F5F8FF)
                mNavMyLayout.setBackgroundResource(R.color.color_F5F8FF)

            }
            1 -> {
                mNavPhotoImg.setImageResource(R.mipmap.icon_photo_normal)
                mNavHealthImg.setImageResource(R.mipmap.icon_health_select)
                mNavMyImg.setImageResource(R.mipmap.icon_mine_normal)
                mNavPhotoTv.setTextColor(ContextCompat.getColor(context, R.color.nav_text_normal))
                mNavHealthTv.setTextColor(ContextCompat.getColor(context, R.color.nav_text_selected))
                mNavMyTv.setTextColor(ContextCompat.getColor(context, R.color.nav_text_normal))
                mNavPhotoLayout.setBackgroundResource(R.color.color_F5F8FF)
                mNavHealthLayout.setBackgroundResource(R.drawable.bottom_nvg_selected_rectangle)
                mNavMyLayout.setBackgroundResource(R.color.color_F5F8FF)

            }
            2 -> {
                mNavPhotoImg.setImageResource(R.mipmap.icon_photo_normal)
                mNavHealthImg.setImageResource(R.mipmap.icon_health_normal)
                mNavMyImg.setImageResource(R.mipmap.icon_mine_selected)
                mNavPhotoTv.setTextColor(ContextCompat.getColor(context, R.color.nav_text_normal))
                mNavHealthTv.setTextColor(ContextCompat.getColor(context, R.color.nav_text_normal))
                mNavMyTv.setTextColor(ContextCompat.getColor(context, R.color.nav_text_selected))
                mNavPhotoLayout.setBackgroundResource(R.color.color_F5F8FF)
                mNavHealthLayout.setBackgroundResource(R.color.color_F5F8FF)
                mNavMyLayout.setBackgroundResource(R.drawable.bottom_nvg_selected_rectangle)

            }
        }
    }

    interface OnTabSelectedListener {

        /**
         * Called when a tab enters the selected state.
         *
         * @param position The position of the tab that was selected
         */
        fun onTabSelected(position: Int)
    }

}