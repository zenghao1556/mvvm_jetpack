package com.zh.frame.library_network.http.converter

import com.alibaba.fastjson.JSON
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Converter

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.library_network.http.converter
 * @ClassName: FastJsonRequestBodyConverter
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/9 5:42 下午
 */
class FastJsonRequestBodyConverter<T>: Converter<T,RequestBody> {
    override fun convert(value: T): RequestBody? {
        return RequestBody.create(MediaType.parse(""), JSON.toJSONBytes(value))
    }
}