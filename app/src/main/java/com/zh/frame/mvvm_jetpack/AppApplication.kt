package com.zh.frame.mvvm_jetpack

import com.alibaba.android.arouter.launcher.ARouter
import com.zh.frame.base_lib.BaseApplication
import com.zh.frame.common_lib.keep_process.KeepLifeHelper
import com.zh.frame.common_lib.viewmodel.AppViewModel


/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.mvvm_jetpack
 * @ClassName: AppApplication
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/6 6:13 下午
 */

//全局的viewmodel
val appViewModel: AppViewModel by lazy {
    AppApplication.appViewModelInstance
}


class AppApplication: BaseApplication() {

    companion object{
        lateinit var appViewModelInstance: AppViewModel

    }
    override fun onCreate() {
        super.onCreate()
        appViewModelInstance = getAppViewModelProvider().get(AppViewModel::class.java)
        KeepLifeHelper.createAccount()
        KeepLifeHelper.asyncAccount()


        ///初始化阿里的ARouter
        if (isDebug){
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)

    }


}