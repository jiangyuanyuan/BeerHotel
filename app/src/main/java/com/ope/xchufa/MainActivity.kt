package com.ope.xchufa


import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route

import com.ope.base.common.BaseActivity
import com.ope.provider.router.RouterPath


@Route(path = RouterPath.Main.PATH_MAIN)
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
    }



}
