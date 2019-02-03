package com.beer.baselibrary.view.state

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.beer.baselibrary.R

import uk.co.jamiecruwys.StatefulView
import uk.co.jamiecruwys.ViewState

/**
 *
 *LOADING(0, styleable.StatefulView_loadingLayout, "loadingLayout"),
 *EMPTY(1, styleable.StatefulView_emptyLayout, "emptyLayout"),
 *LOADED(2, styleable.StatefulView_loadedLayout, "loadedLayout"),
 *ERROR(3, styleable.StatefulView_errorLayout, "errorLayout");
 *
 * 多种状态切换 view
 *
 */
class StateCocoView(context: Context, attrs: AttributeSet) : StatefulView(context, attrs) {

    protected var defShowLayout: Int = 0
    /**
     * 重试按钮 回调事件  id 指定R.id.stateAgainBtn
     */
    protected var mOnAgainListener: OnAgainListener? = null


    init {

        val attributes = context.theme.obtainStyledAttributes(attrs, uk.co.jamiecruwys.statefulview.R.styleable.StatefulView, 0, 0)
        defShowLayout = attributes.getInt(uk.co.jamiecruwys.statefulview.R.styleable.StatefulView_defLayout, ViewState.LOADED.position)
        attributes.recycle()

        for (viewState in ViewState.values()) {
            if (viewState.position == defShowLayout) {
                setViewState(viewState)
                break
            }
        }
    }

    override fun setViewState(state: ViewState) {
        super.setViewState(state)
        val chlidrenView = currentView
        val stateAgainBtn = chlidrenView.findViewById<View>(R.id.stateAgainBtn)
        if (stateAgainBtn != null && true) {
            stateAgainBtn.setOnClickListener { view ->
                if (mOnAgainListener != null) {
                    mOnAgainListener!!.onClick(view, state)
                }
            }
        }
    }

    fun setOnAgainListener(mOnAgainListener: OnAgainListener) {
        this.mOnAgainListener = mOnAgainListener
    }

    interface OnAgainListener {
        /**
         * @param view  点击的视图
         * @param state 当前视图类型
         */
        fun onClick(view: View, state: ViewState)
    }
}
