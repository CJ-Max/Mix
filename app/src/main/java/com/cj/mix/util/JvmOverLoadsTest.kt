package com.cj.mix.util

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout

class JvmOverLoadsTest @JvmOverloads constructor(
    val name: String,
    val sex: Int = 1,
    val isChinese: Boolean = true
) {
    // java 不支持函数默认参数值的设置，但是 kotlin 支持
    // kotlin 中设置使用了 @JvmOverloads，在java中使用可带默认参数值设置的，如 SimpleJavaTest中
}
// 通过查看字节码可知道 WrongKoTabBottom 只会构造一个带三个参数的构造函数
// 如果外界想调用一个或者两个参数的构造函数是不行的
class RightKoTabBottom @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
    init {}
}

class WrongKoTabBottom(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {
    init {}
}