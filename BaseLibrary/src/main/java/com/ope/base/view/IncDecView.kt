package com.ope.base.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.ope.base.R
import com.ope.base.helper.loge
import kotlinx.android.synthetic.main.inc_dec_view.view.*
import java.math.BigDecimal

class IncDecView@JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr)  {

    private var text  :String? = null
    private var hint :String? = null

    init {
        //获取自定义属性
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.IncDecView)
        text = typedArray.getString(R.styleable.IncDecView_et_text)
        hint = typedArray.getString(R.styleable.IncDecView_et_hint)


        initView()
        typedArray.recycle()

    }

    private fun initView() {
        val view = View.inflate(context,R.layout.inc_dec_view,this)

        if (hint!=null) mContentEt?.hint= hint

        if (text!=null) mContentEt?.setText(text?.toCharArray(), 0, text?.length?:0)


        view.inc.setOnClickListener {
            val temptext = mContentEt?.text?.toString()
            if (temptext!=null&&temptext.isNotBlank()){
                var accuracy = "1"
                if (temptext.contains(".")){
                    var tempPoint = temptext.length - temptext.indexOf(".")
                    for (i in 1 until tempPoint-1){
                        accuracy = "0$accuracy"
                    }
                    accuracy = "0.$accuracy"
                }
                val accuracyBigDecimal =BigDecimal(accuracy)
                val textBigDecimal =BigDecimal(temptext)
                setText(textBigDecimal.add(accuracyBigDecimal).toPlainString())
            }else{
                setText("1")
            }

        }

        view.dec.setOnClickListener {
            val temptext = mContentEt?.text?.toString()
            if (temptext!=null&&temptext.isNotBlank()){
                val textBigDecimal =BigDecimal(temptext)
                if (textBigDecimal.toDouble()<=0)return@setOnClickListener
                var accuracy = "1"
                if (temptext.contains(".")){
                    val tempPoint = temptext.length - temptext.indexOf(".")
                    for (i in 1 until tempPoint-1){
                        accuracy = "0$accuracy"
                    }
                    accuracy = "0.$accuracy"
                }
                val accuracyBigDecimal =BigDecimal(accuracy)
                setText(textBigDecimal.subtract(accuracyBigDecimal).toPlainString())
            }

        }
    }

    fun setText(text:String){
        if (text!=null){
            mContentEt?.setText(text?.toCharArray(), 0, text?.length?:0)
            mContentEt?.setSelection(text.length)
        }
    }
    fun getText():String{
       return mContentEt?.text?.toString()?:"0"
    }

    fun setHint(text:String){
        if (text!=null){
            mContentEt?.hint = text
        }
    }
    fun getHint():String{
        return mContentEt?.hint?.toString()?:""
    }



}




