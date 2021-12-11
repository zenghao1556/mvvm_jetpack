package com.zh.frame.library_network.http.converter

import com.alibaba.fastjson.JSON
import okhttp3.ResponseBody
import okio.Okio
import retrofit2.Converter
import java.lang.reflect.Type

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.library_network.http.converter
 * @ClassName: FastJsonResponseBodyConverter
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/9 5:34 下午
 */
class FastJsonResponseBodyConverter<T>(type: Type) : Converter<ResponseBody,T> {
    private  val type = type

    override fun convert(value: ResponseBody): T{
        var bufferedSource = Okio.buffer(value.source())
        var tempStr = bufferedSource.readUtf8()
        bufferedSource.close()
        return JSON.parseObject(tempStr,type)
    }
}