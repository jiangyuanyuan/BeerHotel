//package com.iwith.base.common
//
//import android.content.Context
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.alibaba.android.arouter.launcher.ARouter
//import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
//import me.yokeyword.fragmentation.SupportFragment
//import me.yokeyword.fragmentation_swipeback.SwipeBackFragment
//
//abstract class BaseBackFragment : SwipeBackFragment() {
//
//    protected val scopeProvider by lazy { AndroidLifecycleScopeProvider.from(this) }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        injectComponent()
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        ARouter.getInstance().inject(this)
//    }
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(getLayoutId(), container, false)
//        return attachToSwipeBack(view)
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        setParallaxOffset(0.5f)
//    }
//
//    abstract fun getLayoutId(): Int
//
//    abstract fun injectComponent()
//}