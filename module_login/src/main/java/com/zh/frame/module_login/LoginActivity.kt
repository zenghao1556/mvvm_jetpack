package com.zh.frame.module_login

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.alibaba.android.arouter.facade.annotation.Route
import com.zh.frame.base_lib.activity.BaseActivity
import com.zh.frame.module_login.databinding.ActivityLoginBinding
import com.zh.frame.module_login.viewmodel.LoginViewModel

@Route(path = "/login/LoginActivity")
class LoginActivity : BaseActivity<LoginViewModel,ActivityLoginBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDataBinding.model = mViewModel
    }

    override fun layoutId(): Int {
        return R.layout.activity_login
    }


}