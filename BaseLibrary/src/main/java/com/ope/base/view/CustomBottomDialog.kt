package com.ope.base.view

import android.app.Dialog
import android.content.Context
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import com.ope.base.R




class CustomBottomDialog(private val context: Context) {
    private var tv_takephoto: TextView? = null
    private lateinit var tv_photo: TextView
    private lateinit var tv_cancle: TextView
    private lateinit var mListener: OnListener
    private lateinit var mView: View
    private var dialog: Dialog? = null

    fun builder(): CustomBottomDialog {
        if (dialog == null) {
            mView = LayoutInflater.from(context).inflate(R.layout.dialog_custom_bottom, null)
            dialog = Dialog(context, R.style.AlertDialogStyle)
            dialog!!.setCancelable(true)
            dialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog!!.setContentView(mView)
            val window = dialog!!.window
            window!!.setGravity(Gravity.BOTTOM)
            val lp = window.attributes
            val manager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val dm = DisplayMetrics()
            manager.defaultDisplay.getMetrics(dm)
            val width = dm.widthPixels
            lp.width = width
            window.attributes = lp
        }
        initView()
        return this
    }

    private fun initView() {
        tv_takephoto = mView.findViewById<View>(R.id.tv_takephoto) as TextView
        tv_cancle = mView.findViewById<View>(R.id.tv_cancel) as TextView
        tv_photo = mView.findViewById<View>(R.id.tv_photo) as TextView
        tv_cancle.setOnClickListener { dialog!!.dismiss() }
        tv_takephoto!!.setOnClickListener {
            if (mListener != null) {
                mListener.onOneClick()
            }

            dialog!!.dismiss()
        }
        tv_photo.setOnClickListener {
            if (mListener != null) {
                mListener.onTwoClick()
            }
            dialog!!.dismiss()
        }
    }

    fun setOneText(text: String) {
        tv_takephoto!!.text = text
    }

    fun setTwoText(text: String) {
        tv_photo.text = text
    }


    fun setCancelable(cancel: Boolean): CustomBottomDialog {
        dialog!!.setCancelable(cancel)
        return this
    }

    fun show() {
        dialog!!.show()
    }

    fun setTakePhotoListener(listener: OnListener): CustomBottomDialog {
        mListener = listener
        return this
    }

    interface OnListener {
        fun onOneClick()
        fun onTwoClick()
    }
}
