package com.zh.frame.common_lib.viewmodel

import androidx.lifecycle.viewModelScope
import com.zh.frame.base_lib.viewmodel.BaseViewModel
import com.zh.frame.library_network.http.exceptiion.AppException
import com.zh.frame.library_network.base.BaseResponse
import com.zh.frame.library_network.http.exceptiion.ExceptionHandle
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.library_network.request
 * @ClassName: RequestExt
 * @Description: request的扩展函数   如果有其它类型的可以再添加类似的
 * @Author: 曾浩
 * @CreateDate: 2021/12/11 4:58 下午
 */
fun <T> BaseViewModel.request(
    block: suspend () -> BaseResponse<T>,
    success: (T) -> Unit,
    error: (AppException) -> Unit = {},
    isShowDialog: Boolean = false,
):Job{
    return viewModelScope.launch {
        runCatching {
            if (isShowDialog) loadingChange.showLoading.postValue("请求网络中...")
            block()
        }.onSuccess {
            if (it.getCode() == 0){
                success(it.getResult())
            }else{
                error(AppException(it.getCode(),it.getErrorMessage()))
            }
        }.onFailure {
            error(ExceptionHandle.handleException(it))
        }
    }
}


fun <R> chainRequest(action:()->R) = Helper(action())

class Helper<T>(var item:T){
    fun <R> map(action:T.() ->R) = Helper(action(item))
    fun observer(action:T.() -> Unit) = Helper(action(item))
}
