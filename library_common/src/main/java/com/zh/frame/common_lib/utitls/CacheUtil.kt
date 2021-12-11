package com.zh.frame.common_lib.utitls

import com.zh.frame.common_lib.bean.UserInfo
import com.zh.frame.common_lib.storage.Constant
import com.zh.frame.common_lib.storage.MmkvHelper

object CacheUtil {
    /**
     * 获取保存的账户信息
     */
    fun getUser(): UserInfo? {
        return MmkvHelper.instance.getData(Constant.USER_INFO_KEY,UserInfo()) as UserInfo?
    }

    /**
     * 设置账户信息
     */
    fun setUser(userResponse: UserInfo) {
        MmkvHelper.instance.saveData(Constant.USER_INFO_KEY,userResponse)

    }

    /**
     * 是否是第一次登陆
     */
    fun isFirst(): Boolean {
        return MmkvHelper.instance.getData(Constant.FIRST_LOGIN_KEY,false) as Boolean
    }

}