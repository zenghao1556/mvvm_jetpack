package com.zh.frame.base_lib.viewmodel

import androidx.lifecycle.ViewModel
import com.zh.frame.base_lib.model.CustomLiveData

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.base_lib.viewmodel
 * @ClassName: BaseViewModel
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/7 6:21 下午
 */
open class BaseViewModel :IBaseViewModel, ViewModel() {

    val loadingChange:LoadingChange by lazy {
        LoadingChange()
    }

    inner class LoadingChange{
        val showLoading by lazy { CustomLiveData<String>() }
        val dismissLoading by lazy { CustomLiveData<Boolean>() }
    }
}