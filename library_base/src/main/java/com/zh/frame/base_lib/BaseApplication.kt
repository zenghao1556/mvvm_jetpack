package com.zh.frame.base_lib

import android.app.Application
import android.content.pm.ApplicationInfo
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.alibaba.android.arouter.launcher.ARouter
import com.tencent.mmkv.MMKV

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.base_lib
 * @ClassName: BaseApplication
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/6 5:57 下午
 */

open class BaseApplication: Application(),ViewModelStoreOwner {
    private lateinit var mAppViewModelStore: ViewModelStore

    private var mFactory: ViewModelProvider.Factory? = null

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

    companion object{
            lateinit var application: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        mAppViewModelStore = ViewModelStore()

        //初始化MMKV
        MMKV.initialize(this)
        //初始化全局viewModel
        application = this

        ///初始化阿里的ARouter
        if (isDebug){
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    /**
     * 获取一个全局的ViewModel
     */
    fun getAppViewModelProvider(): ViewModelProvider {
        return ViewModelProvider(this, this.getAppFactory())
    }

    private fun getAppFactory(): ViewModelProvider.Factory {
        if (mFactory == null) {
            mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
        }
        return mFactory as ViewModelProvider.Factory
    }


    val isDebug:Boolean by lazy {
        applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE !== 0
    }
}