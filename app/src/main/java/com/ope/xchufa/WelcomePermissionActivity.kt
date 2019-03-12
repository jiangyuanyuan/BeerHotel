package com.ope.xchufa

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.ope.base.common.BaseActivity
import com.ope.base.data.dto.*
import com.ope.base.helper.SystemUtil
import com.ope.base.helper.getAppProcessName
import com.ope.base.helper.getDeviceID
import com.ope.provider.router.RouterPath
import com.ope.xchufa.ext.isLogin
import io.reactivex.Observable
import permissions.dispatcher.*
import java.util.concurrent.TimeUnit


@RuntimePermissions
@Route(path = RouterPath.Main.PATH_WELCOME)
open class WelcomePermissionActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_welcome)
        showStorageWithPermissionCheck()

        APPID = getAppProcessName()
        DEVICEID =  getDeviceID()
        OSVERSION = SystemUtil.systemVersion
        SCREENSIZE = SystemUtil.getScreen(this)
        APIVERSION = SystemUtil.getVersionName(this)
    }

    @NeedsPermission(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA)
    fun showStorage() {
        Toast.makeText(this, getString(R.string.permission_opened), Toast.LENGTH_SHORT).show()
    }

    @OnShowRationale(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA)
    fun showRationaleForStorage(request: PermissionRequest) {
        showRationaleDialog(getString(R.string.permission_rationale), request)
    }

    @OnPermissionDenied(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA)
    fun onStorageDenied() {
        Toast.makeText(this, getString(R.string.permission_denied), Toast.LENGTH_SHORT).show()

    }

    @OnNeverAskAgain(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_CONTACTS,
            Manifest.permission.GET_ACCOUNTS,
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CAMERA)
    fun onStorageNeverAskAgain() {
       // Toast.makeText(this, getString(R.string.permission_again), Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
        toMain()
    }

    private fun showRationaleDialog(messageResId: String, request: PermissionRequest) {
        AlertDialog.Builder(this)
                .setPositiveButton(getString(R.string.permission_dialog_allow)) { _, _ -> request.proceed() }
                .setNegativeButton(getString(R.string.permission_dialog_deny)) { _, _ ->
                    toMain()
                    request.cancel()
                }
                .setCancelable(false)
                .setMessage(messageResId)
                .show()
    }


    @SuppressLint("CheckResult")
    fun toMain(){
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribe {
//                    ARouter.getInstance().build(RouterPath.MyCenter.PATH_LOGIN).navigation()//TODO  框架启动 会黑屏
                        startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
    }

}