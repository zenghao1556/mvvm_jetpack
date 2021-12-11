package com.zh.frame.common_lib.keep_process

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.common_lib.keep_process
 * @ClassName: AsyncProvider
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/10 6:28 下午
 */
class AsyncProvider:ContentProvider() {
    override fun onCreate(): Boolean {
        return false
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}