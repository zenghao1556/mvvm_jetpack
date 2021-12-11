package com.zh.frame.library_network.http.converter

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.library_network.http.converter
 * @ClassName: FastJsonConverterFactory
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/9 5:30 下午
 */
object FastJsonConverterFactory: Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return FastJsonResponseBodyConverter<Any>(type)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        return FastJsonRequestBodyConverter<Any>()
    }
}

