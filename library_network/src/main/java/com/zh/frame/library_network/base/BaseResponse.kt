package com.zh.frame.library_network.base

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.base_lib.reponse
 * @ClassName: BaseResponse
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/9 2:32 下午
 */
abstract class BaseResponse<T> {
    abstract fun getCode():Int
    abstract fun getResult():T?
    abstract fun getErrorMessage():String?
    abstract fun isSucces():Boolean
}