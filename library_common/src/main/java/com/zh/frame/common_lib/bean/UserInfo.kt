package com.zh.frame.common_lib.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.base_lib.model.bean
 * @ClassName: UserInfo
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/8 2:06 下午
 */
@SuppressLint
@Parcelize
data class UserInfo(var phone:String = "",
                    var pwd:String = "",
                    var nikeName:String = "",
                    var six:Int = 1,
                    var token:String="",
                    var headImg:String = ""
                    ): Parcelable
