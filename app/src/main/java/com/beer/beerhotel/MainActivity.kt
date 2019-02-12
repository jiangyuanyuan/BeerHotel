package com.beer.beerhotel

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.amap.api.maps.AMap
import com.amap.api.maps.CameraUpdateFactory
import kotlinx.android.synthetic.main.activity_main.*
import com.amap.api.maps.model.MyLocationStyle
import android.Manifest.permission
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.widget.Toast
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.beer.baselibrary.common.BaseActivity
import com.beer.baselibrary.common.PermissionActivity


class MainActivity : BaseActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState)
        val aMap = mMapView.map

//        //设置地图的放缩级别
        aMap.moveCamera(CameraUpdateFactory.zoomTo(13f));
//初始化定位蓝点样式类
        val myLocationStyle =  MyLocationStyle();
//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
//设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.interval(2000);
//设置定位蓝点的Style
        aMap.setMyLocationStyle(myLocationStyle);
//设置默认定位按钮是否显示，非必需设置。
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setMyLocationEnabled(true);
    }


    override fun onDestroy() {
        super.onDestroy()

        mMapView.onDestroy()
    }


    override fun onResume() {
        super.onResume()

        mMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mMapView.onSaveInstanceState(outState)
    }
}
