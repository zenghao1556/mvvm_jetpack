package com.zh.frame.library_network.reponse.login

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.module_login.response
 * @ClassName: LoginResponse
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/9 2:47 下午
 */
@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "name") var name:String = "",
    @Json(name = "nickname") var nickname:String = ""
)
