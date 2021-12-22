package com.zh.frame.module_login.viewmodel

import android.widget.Toast
import com.zh.frame.base_lib.BaseApplication
import com.zh.frame.base_lib.viewmodel.BaseViewModel
import com.zh.frame.common_lib.livebus.CustomLiveData
import com.zh.frame.common_lib.viewmodel.request
import com.zh.frame.library_network.http.HttpClient


/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.module_login.viewmodel
 * @ClassName: LoginViewModel
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/10 11:17 上午
 */
class LoginViewModel: BaseViewModel(){

    var userName:String = "12345"
    var password:String = "12345"

    var btnName:CustomLiveData<String> = CustomLiveData()


    fun login(){
        //使用扩展函数 对数据结构进行解析 包括异常处理
        request(
            {
                HttpClient.instance.getApi().login(userName,password)
            },
            {
                Toast.makeText(BaseApplication.application,"登录成功",Toast.LENGTH_SHORT).show()
            },{
                Toast.makeText(BaseApplication.application,it.errorMsg,Toast.LENGTH_SHORT).show()
            },
            true
        )
    }




}