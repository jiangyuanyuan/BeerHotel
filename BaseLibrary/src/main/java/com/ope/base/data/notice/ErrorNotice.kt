package com.ope.base.data.notice

class ErrorNotice private constructor() {


    private var mErrorListener: ErrorListener? = null

    fun reg(listener: ErrorListener) {
        this.mErrorListener = listener
    }

    fun notifyError(code: String, msg: String) {
        if (null != mErrorListener) {
            mErrorListener!!.onNotify(code, msg)
        }
    }

    interface ErrorListener {
        fun onNotify(code: String, msg: String)
    }

    companion object {

        val INSTANCE : ErrorNotice by lazy {
            ErrorNotice()
        }

    }
}
