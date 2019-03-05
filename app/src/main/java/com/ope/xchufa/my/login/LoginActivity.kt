package com.ope.xchufa.my.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.ope.base.common.BaseActivity
import com.ope.provider.router.RouterPath
import com.ope.xchufa.R
@Route(path = RouterPath.MyCenter.PATH_LOGIN)
class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
