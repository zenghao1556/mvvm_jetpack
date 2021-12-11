package com.zh.frame.common_lib.keep_process

import android.accounts.Account
import android.app.Service
import android.content.*
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import com.zh.frame.base_lib.BaseApplication

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.common_lib.service
 * @ClassName: AccountSyncService
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/10 6:23 下午
 */
class AccountAsyncService: Service() {

    private lateinit var mThreadSyncAdapter:ThreadSyncAdapter

    override fun onCreate() {
        super.onCreate()
        mThreadSyncAdapter = ThreadSyncAdapter(BaseApplication.application, true)
    }
    override fun onBind(intent: Intent?): IBinder? {
        return mThreadSyncAdapter.syncAdapterBinder
    }


    class ThreadSyncAdapter(context: Context?, autoInitialize: Boolean) :
        AbstractThreadedSyncAdapter(context, autoInitialize) {
        override fun onPerformSync(
            account: Account, extras: Bundle, authority: String,
            provider: ContentProviderClient, syncResult: SyncResult
        ) {
            // 账户同步操作
            // 与数据库 , 服务器同步操作 , 这里只是为了应用进程拉活 , 不实现具体的逻辑
            Log.i("AccountSyncService", "账户同步拉活激活")
        }
    }
}