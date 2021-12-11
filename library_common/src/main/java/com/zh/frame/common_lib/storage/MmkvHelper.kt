package com.zh.frame.common_lib.storage

import com.alibaba.fastjson.JSONObject
import com.tencent.mmkv.MMKV

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.base_lib.storage
 * @ClassName: MmkvHelper
 * @Description: mmkv操作单例类
 * @Author: 曾浩
 * @CreateDate: 2021/12/6 4:57 下午
 */
class MmkvHelper{

    private var mmkv: MMKV = MMKV.defaultMMKV()

    private constructor()

    companion object{
        val instance:MmkvHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            MmkvHelper()
        }
    }

    fun saveData(key:String,data:Any){
        when (data) {
            is String -> {
                mmkv.putString(key,data)
            }
            is Int -> {
                mmkv.putInt(key,data)
            }
            is Boolean -> {
                mmkv.putBoolean(key,data)
            }
            is Map<*,*> ->{
                mmkv.putString(key,JSONObject.toJSONString(data))
            }
            is ByteArray ->{
                mmkv.putBytes(key,data)
            }
            is Float ->{
                mmkv.putFloat(key,data)
            }
            is Long ->{
                mmkv.putLong(key,data)
            }
        }

    }

    fun getData(key:String,default:Any):Any?{
        return when (default) {
            is String -> {
                return mmkv.decodeString(key)
            }
            is Int -> {
                return mmkv.decodeInt(key)
            }
            is Boolean -> {
                return mmkv.decodeBool(key)
            }
            is Map<*, *> -> {
                return JSONObject.toJSON(mmkv.decodeString(key))
            }
            is ByteArray -> {
                return mmkv.decodeBytes(key)
            }
            is Float -> {
                return mmkv.decodeFloat(key)
            }
            is Long -> {
                return mmkv.decodeLong(key)
            }
            else -> {
                null
            }
        }
    }

}