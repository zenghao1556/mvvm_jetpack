package com.zh.frame.common_lib.keep_process

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.common_lib.keep_process
 * @ClassName: AccountService
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/10 6:27 下午
 */
class AccountService:Service() {
    lateinit var accountAuthenticator: AbstractAccountAuthenticator

    override fun onCreate() {
        super.onCreate()
        accountAuthenticator = AccountAuthenticator(this)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return accountAuthenticator.iBinder
    }

    class AccountAuthenticator(context:Context):AbstractAccountAuthenticator(context){

        override fun editProperties(
            response: AccountAuthenticatorResponse?,
            accountType: String?
        ): Bundle? {
            return null
        }

        override fun addAccount(
            response: AccountAuthenticatorResponse?,
            accountType: String?,
            authTokenType: String?,
            requiredFeatures: Array<out String>?,
            options: Bundle?
        ): Bundle? {
            return null
        }

        override fun confirmCredentials(
            response: AccountAuthenticatorResponse?,
            account: Account?,
            options: Bundle?
        ): Bundle? {
            return null
        }

        override fun getAuthToken(
            response: AccountAuthenticatorResponse?,
            account: Account?,
            authTokenType: String?,
            options: Bundle?
        ): Bundle? {
            return null
        }

        override fun getAuthTokenLabel(authTokenType: String?): String? {
            return null
        }

        override fun updateCredentials(
            response: AccountAuthenticatorResponse?,
            account: Account?,
            authTokenType: String?,
            options: Bundle?
        ): Bundle? {
            return null
        }

        override fun hasFeatures(
            response: AccountAuthenticatorResponse?,
            account: Account?,
            features: Array<out String>?
        ): Bundle? {
            return null
        }

    }
}

