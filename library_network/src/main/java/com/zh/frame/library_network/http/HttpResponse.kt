package com.zh.frame.library_network.http

import com.zh.frame.library_network.base.BaseResponse

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.base_lib.reponse
 * @ClassName: HttpResponse
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/9 2:39 下午
 */
data class HttpResponse<T>(val errorCode: Int, val errorMsg: String, val data: T): BaseResponse<T>(){

    override fun getErrorMessage() = errorMsg

    override fun getCode() = errorCode

    override fun getResult() = data

    override fun isSucces() = errorCode == 0


}

