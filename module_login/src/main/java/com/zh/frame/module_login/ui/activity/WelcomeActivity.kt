package com.zh.frame.module_login.ui.activity

import android.os.Bundle
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.zh.frame.base_lib.activity.BaseActivity
import com.zh.frame.module_login.R
import com.zh.frame.module_login.databinding.ActivityWelcomeBinding
import com.zh.frame.module_login.viewmodel.WelcomeViewModel

@Route(path = "/login/WelcomeActivity")
class WelcomeActivity : BaseActivity<WelcomeViewModel,ActivityWelcomeBinding>() {

    private var exitTime:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding.iv.postDelayed({
            ARouter.getInstance().build("/login/LoginActivity").navigation()
            finish() },500)
    }

    override fun layoutId(): Int {
        return R.layout.activity_welcome
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - exitTime>2000){
            Toast.makeText(this,"再点击一次退出应用程序",Toast.LENGTH_SHORT).show()
            exitTime = System.currentTimeMillis()
        }else{
            super.onBackPressed()
        }
    }

    override fun bindViewModel() {
    }
}