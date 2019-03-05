package com.ope.base.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.ope.base.R
import kotlinx.android.synthetic.main.head_layout.view.*

class HeaderBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {



    //是否显示"右边"图标
    private var isShowRight = true

    //是否显示"拍照"图标
    private var isShowCamera = true
    //是否显示"更多"图标
    private var isShowMore = true
    //Title文字
    private var titleText:String? = null
    //右侧文字
    private var rightText:String? = null


    init {
        //获取自定义属性
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar)

//        isShowBack = typedArray.getBoolean(R.styleable.HeaderBar_isShowBack,true)
        isShowRight = typedArray.getBoolean(R.styleable.HeaderBar_isShowRight,false)
//        isShowCancel = typedArray.getBoolean(R.styleable.HeaderBar_isShowCancel,false)
        isShowCamera = typedArray.getBoolean(R.styleable.HeaderBar_isShowCamera,false)
        isShowMore = typedArray.getBoolean(R.styleable.HeaderBar_isShowMore,false)

        titleText = typedArray.getString(R.styleable.HeaderBar_titleText)
        rightText = typedArray.getString(R.styleable.HeaderBar_rightText)

        initView()
        typedArray.recycle()

    }

    private fun initView() {
        View.inflate(context,R.layout.head_layout,this)

//        mBackTv.visibility = if (isShowBack) View.VISIBLE else View.GONE
        mRightTv.visibility = if (isShowRight) View.VISIBLE else View.GONE
//        if (isShowCancel){
//            mCancelTv.visibility = View.VISIBLE
//            mBackTv.visibility = View.GONE
//        }else{
//            mCancelTv.visibility = View.GONE
//        }
        if (isShowCamera){
            mRightTv.visibility = View.GONE
            mCameraLayout.visibility = View.VISIBLE
        }
        if (isShowMore){
            mRightTv.visibility = View.GONE
            mCameraLayout.visibility = View.GONE
            mMoreLayout.visibility = View.VISIBLE
        }

        //标题不为空，设置值
        titleText?.let {
            mTitleTv.text = it
        }

        //右侧文字不为空，设置值
        rightText?.let {
            mRightTv.text = it
            mRightTv.visibility = View.VISIBLE
        }

    }

//    fun setBackVisibility(visibility:Int){
//        mBackTv.visibility = visibility
//    }

//    fun setLeftCancelVisibility(visibility:Int){
//        mCancelTv.visibility = visibility
//    }

    fun setRightVisibility(visibility:Int){
        mRightTv.visibility = visibility
    }

    fun setMoreVisibility(visibility:Int){
        mMoreLayout.visibility = visibility
    }

    fun setTitle(text:String){
        mTitleTv.text = text
    }

    fun setTitle(text:Int){
        mTitleTv.setText(text)
    }

    fun setRightText(text:String){
        mRightTv.text = text
    }

    fun setRightText(text:Int){
        mRightTv.setText(text)
    }

    fun onBackClick(l:OnClickListener){
        mBackTv.setOnClickListener(l)
    }

    fun onRightClick(l:OnClickListener){
        mRightTv.setOnClickListener(l)
    }

//    fun onCancelClick(l:OnClickListener){
//        mCancelTv.setOnClickListener(l)
//    }

    fun onCameraClick(l:OnClickListener){
        mCameraLayout.setOnClickListener(l)
    }

    fun onMoreClick(l:OnClickListener){
        mMoreLayout.setOnClickListener(l)
    }

}