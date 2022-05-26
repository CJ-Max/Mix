package com.cj.mix.util

import android.app.Activity
import android.util.Log

/**
 * Created by SpiderG on 5/25/22.
 * About : 反射调用 kapt 生成类
 */
interface IBindView {
    fun bind(activity: Activity)
}

class CKnifeUtil {
    companion object {
        private const val VIEW_BINDING = "_ViewBinding"

        // activity 调用, 通常view bind 可以在fragment view等其他地方使用(可以进一步完善)
        fun bind(activity: Activity) {
            var className = "${activity.javaClass.name}$VIEW_BINDING"
            try {
                var viewBindingClass = Class.forName(className)
                (viewBindingClass.newInstance() as IBindView).bind(activity)
            } catch (e: Exception) {
                Log.e("CKnifeUtil", "error: $e")
            }
        }
    }
}