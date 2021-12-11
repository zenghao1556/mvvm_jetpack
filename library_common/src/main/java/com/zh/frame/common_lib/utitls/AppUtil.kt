package com.zh.frame.common_lib.utitls

import android.content.Context

/**
*
* @ProjectName: mvvm_jetpack
* @Package: com.zh.frame.common_lib.utitls
* @ClassName: AppUtil
* @Description: 类作用描述
* @Author: 曾浩
* @CreateDate: 2021/12/10 10:15 下午
*/
object AppUtil {

    /**
     * 获取应用名称
     */
    fun getAppName(context:Context):String{
        var packageManager = context.packageManager
        var packageInfo = packageManager.getPackageInfo(context.packageName,0)
        var labRes = packageInfo.applicationInfo.labelRes
        return context.resources.getString(labRes)
    }

    /**
     * 获取包名
     */
    fun getPackageName(context:Context):String{
        var packageManager = context.packageManager
        var packageInfo = packageManager.getPackageInfo(context.packageName,0)
        return packageInfo.packageName
    }

}