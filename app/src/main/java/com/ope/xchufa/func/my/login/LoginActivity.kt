package com.ope.xchufa.func.my.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ope.base.common.BaseActivity
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
import me.yokeyword.fragmentation.ISupportFragment

@Route(path = RouterPath.MyCenter.PATH_LOGIN)
class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loadRootFragment(R.id.mLoginContentFl, ARouter.getInstance().build(RouterPath.MyCenter.PATH_LOGINFRAGMENT).navigation() as ISupportFragment)
    }
}
