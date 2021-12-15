package com.zh.frame.module_login.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.zh.frame.base_lib.activity.BaseActivity
import com.zh.frame.common_lib.viewmodel.dismiss
import com.zh.frame.common_lib.viewmodel.loading
import com.zh.frame.module_login.R
import com.zh.frame.module_login.databinding.ActivityLoginBinding
import com.zh.frame.module_login.viewmodel.LoginViewModel

@Route(path = "/login/LoginActivity")
class LoginActivity : BaseActivity<LoginViewModel,ActivityLoginBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

}