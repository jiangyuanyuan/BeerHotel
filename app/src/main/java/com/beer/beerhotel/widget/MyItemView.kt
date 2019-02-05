package com.beer.beerhotel.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.beer.beerhotel.R

import kotlinx.android.synthetic.main.layout_my_item.view.*

class MyItemView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    init {
        //获取自定义属性
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyItemView)


        val titleText = typedArray.getString(R.styleable.MyItemView_titleText)
        val src = typedArray.getResourceId(R.styleable.MyItemView_imgSrc,0)
        val isShowTopLine = typedArray.getBoolean(R.styleable.MyItemView_isShowTopLine,true)
        val isShowBottomLine = typedArray.getBoolean(R.styleable.MyItemView_isShowBottomLine,true)
        View.inflate(context,R.layout.layout_my_item,this)

        mMyItemImg.setImageResource(src)
        mMyItemTv.text = titleText
//        _line.visibility = if (isShowTopLine) View.VISIBLE else View.GONE
//        line_.visibility = if (isShowBottomLine) View.VISIBLE else View.GONE
        typedArray.recycle()

    }

    fun setText(text:String){
        mMyItemTv.text = text
    }

    fun showDot(number:Int){
        mDotTv.text = "$number"
        mDotTv.visibility = View.VISIBLE
    }

    fun hideDot(){
        mDotTv.visibility = View.GONE
    }

}