package com.zh.frame.common_lib.utitls

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.zh.frame.common_lib.R

/**
 *
 * @ProjectName: mvvm_jetpack
 * @Package: com.zh.frame.common_lib.utitls
 * @ClassName: LoadingDialog
 * @Description: 类作用描述
 * @Author: 曾浩
 * @CreateDate: 2021/12/15 3:17 下午
 */
class LoadingDialog(context: Context,themeResId:Int) : Dialog(context,themeResId) {

    class Builder(context: Context){
        private var mContext = context
        private var isShowContent = true
        private var isCancelable = true
        private var isCancelOutside = false
        private var content = "加载中"

        /**
         * 设置提示内容
         */
        fun setContent(msg:String):Builder{
            content = msg
            return this
        }

        /**
         * 设置是否显示加载框里的内容
         */
        fun setShowContent(showContent:Boolean):Builder{
            isShowContent = showContent
            return this
        }

        /**
         * 是否可以返回键关闭窗口
         */
        fun setCancelable(cancelable:Boolean):Builder{
            isCancelable = cancelable
            return this
        }

        /**
         * 是否点击空白区域关闭
         */
        fun setCancelOutside(cancelOutside:Boolean):Builder{
            isCancelable = cancelOutside
            return this
        }


        fun create():LoadingDialog{
            var inflater = LayoutInflater.from(mContext)
            var view = inflater.inflate(R.layout.loading_dialog,null)
            var loadingDialog = LoadingDialog(mContext,R.style.CustomDialog)
            if (isShowContent){
                view.findViewById<TextView>(R.id.tipTextView).text = content
            }else{
                view.findViewById<TextView>(R.id.tipTextView).visibility = View.GONE
            }
            loadingDialog.setContentView(view)
            loadingDialog.setCancelable(isCancelable)
            loadingDialog.setCanceledOnTouchOutside(isCancelOutside)
            return loadingDialog
        }
    }

}