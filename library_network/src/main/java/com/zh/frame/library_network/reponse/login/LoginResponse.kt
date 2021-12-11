package com.zh.frame.library_network.reponse.login

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.module_login.response
 * @ClassName: LoginResponse
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/9 2:47 下午
 */
@Parcelize
data class LoginResponse(
    var name:String = "",
    var nickname:String = ""
):Parcelable
