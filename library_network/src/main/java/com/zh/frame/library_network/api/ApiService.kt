package com.zh.frame.library_network.api

import com.zh.frame.library_network.http.HttpResponse
import com.zh.frame.library_network.reponse.login.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.library_network.http
 * @ClassName: ApiService
 * @Description: 网络请求这地方我原本想的是要把各个业务模块的api放在各自的模块里，
 * 但是纠结了好久最终还是统一管理，原因是现实中很多api是在各个模块交叉使用的，
 * 如果分开的话会加大api更新时的维护成本，但是为了使代码结构更清晰所以我在response里建了model的分类包
 * @Author: 曾浩
 * @CreateDate: 2021/12/8 4:01 下午
 */
interface ApiService {

    companion object {
        const val SERVER_URL = "https://wanandroid.com/"
    }

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@Field("username") username:String,@Field("password") password:String): HttpResponse<LoginResponse?>

}