package com.zh.frame.common_lib.livebus

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlin.collections.Map.Entry

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.base_lib.model
 * @ClassName: BaseLiveData
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/7 10:02 上午
 */
class CustomLiveData<T>: MutableLiveData<T>() {
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)
        hook(observer)
    }

    override fun setValue(value: T) {
        super.setValue(value)
    }

    override fun postValue(value: T) {
        super.postValue(value)
    }

    private fun hook(observer:Observer<in T>){
        //反射livedata对象
        val data = LiveData::class
        //拿到里面的mObservers属性
        var fileld = data.java.getDeclaredField("mObservers")
        //设置可访问
        fileld.isAccessible = true
        //拿到mObservers对象，其实是个map
        var mObservers = fileld.get(this)
        var classObserver = mObservers.javaClass
        //反射拿到get方法
        var methodGet = classObserver.getDeclaredMethod("get",Object::class.java)
        //设置get方法可访问
        methodGet.isAccessible = true
        //执行get方法拿到map
        var entry:Entry<T,T> = methodGet.invoke(mObservers,observer) as Entry<T, T>
        //拿到map的value
        var objectWrapper = entry.value
        //获取父类的class对象
        var ob:Class<T> = objectWrapper!!::class.java.superclass as Class<T>
        //获取对象里的mLastVersion属性
        var mLastVersion = ob.getDeclaredField("mLastVersion")
        //设置mLastVersion可以访问
        mLastVersion.isAccessible = true

        //反射拿到mVersion属性
        var mVersion = data.java.getDeclaredField("mVersion")
        //设置其可访问
        mVersion.isAccessible = true
        //拿到对应的值
        var mVersionValue = mVersion.get(this)
        //设置给mLastVersion
        mLastVersion.set(objectWrapper,mVersionValue)

    }
}

