package com.zh.frame.library_network.utils

import java.io.IOException
import java.net.HttpURLConnection
import java.net.NetworkInterface
import java.net.SocketException
import java.net.URL

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.library_network.utils
 * @ClassName: NetworkUtil
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/8 4:42 下午
 */
object NetworkUtil {

    /**
     * getLocalIpAddress
     * @return
     */
    fun getLocalIpAddress(): String? {
        var ret = ""
        try {
            val en = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf = en.nextElement()
                val enumIpAddr = intf.inetAddresses
                while (enumIpAddr.hasMoreElements()) {
                    val inetAddress = enumIpAddr.nextElement()
                    if (!inetAddress.isLoopbackAddress) {
                        ret = inetAddress.hostAddress.toString()
                    }
                }
            }
        } catch (ex: SocketException) {
            ex.printStackTrace()
        }
        return ret
    }


    /**
     * ping "http://www.baidu.com"
     *
     * @return
     */
    private fun connectionNetwork(): Boolean {
        var result = false
        var httpUrl: HttpURLConnection? = null
        try {
            httpUrl = URL("http://www.baidu.co").openConnection() as HttpURLConnection
            httpUrl.connectTimeout = 3000
            httpUrl!!.connect()
            result = true
        } catch (e: IOException) {
        } finally {
            httpUrl?.disconnect()
            httpUrl = null
        }
        return result
    }

}