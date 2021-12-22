package com.zh.frame.module_login.ui.activity

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.zh.frame.base_lib.activity.BaseActivity
import com.zh.frame.common_lib.viewmodel.dismiss
import com.zh.frame.common_lib.viewmodel.loading
import com.zh.frame.module_login.R
import com.zh.frame.module_login.databinding.ActivityLoginBinding
import com.zh.frame.module_login.viewmodel.LoginViewModel
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import pub.devrel.easypermissions.PermissionRequest


@Route(path = "/login/LoginActivity")
class LoginActivity : BaseActivity<LoginViewModel,ActivityLoginBinding>(),EasyPermissions.PermissionCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListener()
    }

    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun bindViewModel() {
        mDataBinding.model = mViewModel
    }

    override fun showLoading(content: String) {
        loading(content)
    }

    override fun dismissLoading() {
        dismiss()
    }


    private fun initListener(){
        mDataBinding.loginBtn.setOnClickListener {
            mViewModel.login()
        }

        mDataBinding.testPermissionBtn.setOnClickListener {
            testPermission()
        }
    }



    @AfterPermissionGranted(222)
    private fun testPermission(){
        var permission = Array<String>(1) { Manifest.permission.CAMERA }
        if (EasyPermissions.hasPermissions(this,"android.permission.CAMERA")){
            Toast.makeText(this,"已有相机权限", Toast.LENGTH_SHORT).show()
        }else{
            EasyPermissions.requestPermissions(
                PermissionRequest.Builder(this, 222, "android.permission.CAMERA")
                    .setRationale("23232323")
                    .setPositiveButtonText("ok")
                    .setNegativeButtonText("cancle")
                    .build()
            )
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Toast.makeText(this,"AAA", Toast.LENGTH_SHORT).show()
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Toast.makeText(this,"BBB", Toast.LENGTH_SHORT).show()
    }




}