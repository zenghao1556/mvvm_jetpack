package com.zh.frame.library_network.state

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.library_network.state
 * @ClassName: NetStateReceive
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/8 4:13 下午
 */
class NetStateReceive: BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context?, intent: Intent?) {
        var connectivityManager:ConnectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        var activeNetwork = connectivityManager.activeNetwork

        if (activeNetwork!=null){
            var networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
            if (networkCapabilities!=null){
                if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)){
                    NetStateManager.instance.setNetState(true,2)
                }else if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                    NetStateManager.instance.setNetState(true,1)
                }else{
                    NetStateManager.instance.setNetState(false,0)
                }
            }else{
                NetStateManager.instance.setNetState(false,0)
            }
        }else{
            NetStateManager.instance.setNetState(false,0)
        }
    }
}