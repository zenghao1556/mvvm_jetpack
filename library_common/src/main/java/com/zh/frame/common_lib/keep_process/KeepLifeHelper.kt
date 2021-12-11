package com.zh.frame.common_lib.keep_process

import android.accounts.Account
import android.accounts.AccountManager
import android.content.ContentResolver
import android.content.Context
import android.os.Bundle
import com.zh.frame.base_lib.BaseApplication
import com.zh.frame.common_lib.utitls.AppUtil

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.common_lib.keep_process
 * @ClassName: KeepLifeHelper
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/10 6:30 下午
 */
object KeepLifeHelper {

    /**
     * 创建账户 为了保持账户的唯一性  所以这里type使用了包名
     */
    fun createAccount(){
        val accountManager = BaseApplication.application.getSystemService(Context.ACCOUNT_SERVICE) as AccountManager
        val accounts = accountManager.getAccountsByType(AppUtil.getPackageName(BaseApplication.application))
        if (accounts.isNotEmpty()) {
            return
        }
        val account = Account(AppUtil.getAppName(BaseApplication.application), AppUtil.getPackageName(BaseApplication.application))
        accountManager.addAccountExplicitly(account, "KeepLife", Bundle())
    }

    /**
     * 同步账户
     * authority 这地方注意要和manifest里的provider路径要一致
     */
    fun asyncAccount(){
        val account = Account(AppUtil.getAppName(BaseApplication.application),AppUtil.getPackageName(BaseApplication.application))
        //告诉账户进程开始同步
        //告诉账户进程开始同步
        ContentResolver.setIsSyncable(account, AppUtil.getPackageName(BaseApplication.application)+".provider", 1)
        //自动同步
        //自动同步
        ContentResolver.setSyncAutomatically(account, AppUtil.getPackageName(BaseApplication.application)+".provider", true)
        //增加到同步队列
        //增加到同步队列
        ContentResolver.addPeriodicSync(account, AppUtil.getPackageName(BaseApplication.application)+".provider", Bundle(), 1)
    }
}