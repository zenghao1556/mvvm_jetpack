package com.zh.frame.library_network.http

import android.util.Log
import com.google.gson.GsonBuilder
import com.zh.frame.library_network.api.ApiService
import com.zh.frame.library_network.interceptor.LoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import kotlin.math.log

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.library_network.http
 * @ClassName: HttpClient
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/9 4:55 下午
 */
class HttpClient {

    private constructor(){
        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(ApiService.SERVER_URL)
            .addConverterFactory(MoshiConverterFactory.create())
//            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))//fastjson映射kotlin data类有bug， 但是GSON也有数据转换的bug比如int默认使用double接收的
            .build()
        apiService = retrofit.create(ApiService::class.java)

    }

    companion object{
        private lateinit var apiService:ApiService
        private lateinit var retrofit:Retrofit
        val instance:HttpClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            HttpClient()
        }
    }

    /**
     * 对外暴露的方法
     */
    fun getApi():ApiService = apiService


    /**
     * 配置http
     */
    private val okHttpClient: OkHttpClient
        get() {
            return OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
//                .addInterceptor(LoggingInterceptor())//日志拦截器
                .addInterceptor(HttpLoggingInterceptor {
                    Log.i("http", it)
                }.setLevel(HttpLoggingInterceptor.Level.BODY))
//                .addInterceptor(CacheInterceptor())//缓存拦截器
                .build()
        }
}