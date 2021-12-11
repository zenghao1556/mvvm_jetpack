package com.zh.frame.common_lib.livebus

import android.os.Looper

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.base_lib.model
 * @ClassName: LiveDataBus
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/7 2:39 下午
 */
class LiveDataBus private constructor(){

    private var mLiveDatas = HashMap<String,CustomLiveData<Any>>()

    companion object{
        val instance:LiveDataBus by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            LiveDataBus()
        }
    }

    fun post(key: String,data:Any){
        if (Looper.getMainLooper() == Looper.myLooper()){
            with(key)?.setValue(data)
        }else{
            with(key)?.postValue(data)
        }
    }

    fun remove(key:String){
       if (mLiveDatas.containsKey(key)){
           mLiveDatas.remove(key)
       }
    }

    fun with(key:String):CustomLiveData<Any>{
        if (!mLiveDatas.containsKey(key)){
            mLiveDatas[key] = CustomLiveData()
        }
        return mLiveDatas[key]!!

    }
}