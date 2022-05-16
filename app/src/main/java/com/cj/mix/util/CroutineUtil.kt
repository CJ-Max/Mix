package com.cj.mix.util

import android.view.Surface
import java.io.File
import kotlin.coroutines.createCoroutine

/**
 * Created by SpiderG on 4/16/22.
 * About : 协程相关使用
 */
class CroutineUtil {
    suspend fun suspendFunc(): Int {
        return 1
    }
}