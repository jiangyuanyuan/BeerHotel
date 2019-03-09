package com.ope.xchufa

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter

import com.ope.base.common.PermissionActivity
import com.ope.provider.router.RouterPath
import io.reactivex.Observable
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportFragment
import java.util.concurrent.TimeUnit


@Route(path = RouterPath.Main.PATH_WELCOME)
class WelcomeActivity : PermissionActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_welcome)

        Observable.timer(1500, TimeUnit.MILLISECONDS)
                .subscribe {
                    ARouter.getInstance().build(RouterPath.MyCenter.PATH_LOGIN).navigation()
                    finish()
                }

    }
}