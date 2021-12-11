package com.zh.frame.common_lib.viewmodel

import com.zh.frame.base_lib.viewmodel.BaseViewModel
import com.zh.frame.common_lib.bean.UserInfo

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.base_lib.viewmodel
 * @ClassName: AppViewModel
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/8 2:03 下午
 */
class AppViewModel: BaseViewModel() {
    //全局的用户信息
    var userInfo: UserInfo? = null
}