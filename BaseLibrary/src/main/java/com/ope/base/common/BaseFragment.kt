package com.ope.base.common

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.ope.base.data.ViewModelFactory
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import me.yokeyword.fragmentation.SupportFragment
import javax.inject.Inject

abstract class BaseFragment : SupportFragment() {

    protected val scopeProvider by lazy { AndroidLifecycleScopeProvider.from(this) }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        injectComponent()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    abstract fun getLayoutId(): Int

    abstract fun injectComponent()
}