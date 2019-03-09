package com.ope.base.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.ope.base.R


import kotlinx.android.synthetic.main.dialog_one_text.*

class OneTextDialog
/*  ---------------------------------- 构造方法 -------------------------------------  */
private constructor(builder: Builder, context: Context) : Dialog(context, R.style.MyDialog) {


    private val titleStr: String?
    private val messageStr: String?
    private val yesStr: String?
    private val noStr: String?

    /*  -------------------------------- 接口监听 -------------------------------------  */

    private var yesOnclickListener: ((dialog:OneTextDialog, message:String)->Unit?)? = null
    private var noOnclickListener: ((dialog:OneTextDialog)->Unit?)? = null


    fun setYesListener(mYesOnclickListener: (dialog:OneTextDialog, message:String)->Unit) :OneTextDialog{
        this.yesOnclickListener = mYesOnclickListener
        return this
    }

    fun setNoListener(mNoOnclickListener: (dialog:OneTextDialog)->Unit):OneTextDialog {
        this.noOnclickListener = mNoOnclickListener
        return this
    }

    init {
        this.titleStr = builder.titleStr
        this.messageStr = builder.messageStr
        this.yesStr = builder.yesStr
        this.noStr = builder.noStr
        this.yesOnclickListener = builder.mYesOnclickListener
        this.noOnclickListener = builder.mNoOnclickListener

    }

     class Builder(private val context: Context) {
         var yesStr: String? = null
         var noStr: String? = null
         var messageStr: String? = null
         var titleStr: String? = null
         var mYesOnclickListener: ((dialog:OneTextDialog, message:String)->Unit?)? = null
         var mNoOnclickListener: ((dialog:OneTextDialog)->Unit?)? = null
        fun title(title: String): Builder {
            this.titleStr = title
            return this
        }

        fun message(message: String): Builder {
            this.messageStr = message
            return this
        }

        fun yes(yes: String): Builder {
            this.yesStr = yes
            return this
        }

        fun no(no: String): Builder {
            this.noStr = no
            return this
        }

        fun setPositiveButton(mOnclickListener: (dialog:OneTextDialog, message:String)->Unit): Builder {
            this.mYesOnclickListener = mOnclickListener
            return this
        }

        fun setNegativeButton(mOnclickListener: (dialog:OneTextDialog)->Unit): Builder {
            this.mNoOnclickListener = mOnclickListener
            return this
        }

        fun build(): OneTextDialog {
            return OneTextDialog(this, context)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_one_text)//自定义布局
        //按空白处不能取消动画
        //        setCanceledOnTouchOutside(false);

        //初始化界面数据
        initData()
        //初始化界面控件的事件
        initEvent()

    }

    /**
     * 初始化界面的确定和取消监听器
     */
    private fun initEvent() {
        //设置确定按钮被点击后，向外界提供监听
        yes.setOnClickListener {
            if ( message.text.toString().isNotEmpty()) {
                yesOnclickListener?.invoke(this@OneTextDialog, message.text.toString())
            }
        }
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener {
                noOnclickListener?.invoke(this@OneTextDialog)
        }
    }

    /**
     * 初始化界面控件的显示数据
     */
    private fun initData() {
        //如果用户自定了title和message
        if (messageStr != null) {
            message!!.setText(messageStr)
        }
        if (titleStr != null) {
            title!!.text = titleStr
        }else {
            title.visibility = View.INVISIBLE
        }
        //如果设置按钮的文字
        if (yesStr != null) {
            yes!!.text = yesStr
        }else{
            yes.visibility = View.INVISIBLE
            mView.visibility = View.INVISIBLE
        }
        if (noStr != null) {
            no!!.text = noStr
        }else{
            no.visibility = View.INVISIBLE
            mView.visibility = View.INVISIBLE
        }
    }

}
