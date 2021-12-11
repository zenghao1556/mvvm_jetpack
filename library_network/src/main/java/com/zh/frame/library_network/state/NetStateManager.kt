package com.zh.frame.library_network.state

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.library_network.state
 * @ClassName: NetStateManager
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/8 4:00 下午
 */
class NetStateManager private constructor(){
    private var netState = false
    //网络类型1:WIFI  2:2G、3G、4G  暂时写两种后续再扩展
    private var netType = 1

    companion object{
        val instance :NetStateManager by lazy (mode = LazyThreadSafetyMode.SYNCHRONIZED){
            NetStateManager()
        }
    }

    fun getNetState():Boolean{
        return netState
    }

    fun setNetState(state:Boolean,type:Int){
        netState = state
        netType = type
    }

    /**
     * 判断是否是wifi
     */
    val networkIsWIFI:() ->Boolean = {
        netType == 1
    }
}