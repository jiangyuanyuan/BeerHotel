package com.ope.base.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import com.ope.base.R
import kotlinx.android.synthetic.main.dialog_numberpicker.*

import android.util.DisplayMetrics
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import com.ope.base.helper.loge


class WheelPickerDialog
/*  ---------------------------------- 构造方法 -------------------------------------  */
private constructor(builder: Builder, context: Context) : Dialog(context, R.style.AlertDialogStyle) {


    private val titleStr: String?
    private val messageList: List<String>?
    private val yesStr: String?
    private val noStr: String?
    /*  -------------------------------- 接口监听 -------------------------------------  */

    private var yesOnclickListener: ((dialog:WheelPickerDialog, message:String)->Unit?)? = null
    private var noOnclickListener: ((dialog:WheelPickerDialog)->Unit?)? = null


    fun setYesListener(mYesOnclickListener: (dialog:WheelPickerDialog, message:String)->Unit) :WheelPickerDialog{
        this.yesOnclickListener = mYesOnclickListener
        return this
    }

    fun setNoListener(mNoOnclickListener: (dialog:WheelPickerDialog)->Unit):WheelPickerDialog {
        this.noOnclickListener = mNoOnclickListener
        return this
    }

    init {
        this.titleStr = builder.titleStr
        this.messageList = builder.messageList
        this.yesStr = builder.yesStr
        this.noStr = builder.noStr
        this.yesOnclickListener = builder.mYesOnclickListener
        this.noOnclickListener = builder.mNoOnclickListener

    }

     class Builder(private val context: Context) {
         var yesStr: String? = null
         var noStr: String? = null
         var messageList: List<String>? = null
         var titleStr: String? = null
         var mYesOnclickListener: ((dialog:WheelPickerDialog, message:String)->Unit?)? = null
         var mNoOnclickListener: ((dialog:WheelPickerDialog)->Unit?)? = null
        fun title(title: String): Builder {
            this.titleStr = title
            return this
        }

        fun message(message: List<String>): Builder {
            this.messageList = message
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

        fun setPositiveButton(mOnclickListener: (dialog:WheelPickerDialog, message:String)->Unit): Builder {
            this.mYesOnclickListener = mOnclickListener
            return this
        }

        fun setNegativeButton(mOnclickListener: (dialog:WheelPickerDialog)->Unit): Builder {
            this.mNoOnclickListener = mOnclickListener
            return this
        }

        fun build(): WheelPickerDialog {

            val dialog =WheelPickerDialog(this, context)
            dialog!!.setCancelable(true)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setContentView(R.layout.dialog_numberpicker)
            val window = dialog!!.window
            window!!.setGravity(Gravity.BOTTOM)
            val lp = window.attributes
            val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val dm = DisplayMetrics()
            manager.defaultDisplay.getMetrics(dm)
            val width = dm.widthPixels
            lp.width = width
            window.attributes = lp

            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            yesOnclickListener?.invoke(this@WheelPickerDialog, message.data[message.currentItemPosition].toString())
        }
        //设置取消按钮被点击后，向外界提供监听
        no.setOnClickListener {
                noOnclickListener?.invoke(this@WheelPickerDialog)
        }

    }

    /**
     * 初始化界面控件的显示数据
     */
    private fun initData() {
        //如果用户自定了title和message
        if (messageList != null &&messageList?.size>0) {
            message.data = messageList
        }else {
            message.data = mutableListOf("Australia（澳大利亚）", "China(中国)", "U.S.A(美国)", "Britain(英国)", "Russia(俄罗斯)")
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
        }
        if (noStr != null) {
            no!!.text = noStr
        }else{
            no.visibility = View.INVISIBLE
        }

    }

}
