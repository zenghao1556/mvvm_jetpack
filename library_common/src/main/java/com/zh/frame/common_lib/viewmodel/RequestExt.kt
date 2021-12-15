package com.zh.frame.common_lib.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewModelScope
import com.zh.frame.base_lib.viewmodel.BaseViewModel
import com.zh.frame.common_lib.utitls.LoadingDialog
import com.zh.frame.library_network.http.exceptiion.AppException
import com.zh.frame.library_network.base.BaseResponse
import com.zh.frame.library_network.http.exceptiion.ExceptionHandle
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch



private var loadingDialog:LoadingDialog? =null


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
                loadingChange.dismissLoading.postValue(true)
                success(it.getResult())
            }else{
                loadingChange.dismissLoading.postValue(true)
                error(AppException(it.getCode(),it.getErrorMessage()))
            }
        }.onFailure {
            loadingChange.dismissLoading.postValue(true)
            error(ExceptionHandle.handleException(it))
        }
    }
}


/**
 * 对actvity进行扩展
 * 方便在进行网络请求时进行显示loading窗口
 * 这样做的好处就是把common和base进行解耦，否则我就直接在baseActivity里写了
 * 但是这个地方还是有优化空间的，只能在实际业务开发中慢慢摸索了
 */
fun AppCompatActivity.loading(content:String){
    if (loadingDialog == null){
        loadingDialog = LoadingDialog.Builder(this).setContent(content).create()
    }
    if (!loadingDialog!!.isShowing){
        loadingDialog!!.show()
    }
}

fun AppCompatActivity.dismiss(){
    loadingDialog?.let {
        it.dismiss()
    }
}




fun <R> chainRequest(action:()->R) = Helper(action())

class Helper<T>(var item:T){
    fun <R> map(action:T.() ->R) = Helper(action(item))
    fun observer(action:T.() -> Unit) = Helper(action(item))
}
