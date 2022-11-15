package com.cj.mix.util

import android.util.Log
import kotlinx.coroutines.*
import java.lang.Thread.sleep
import kotlin.concurrent.thread
import kotlin.coroutines.*

/**
 * Created by SpiderG on 4/16/22.
 * About :
 */
class CoroutineTest {
}

fun main() {
    println("start ${Thread.currentThread().name}")
    var b = suspend {
        val a = hello()
        a
    }
    b.createCoroutine(MyContinuation())
    println("end")
    // 是一个suspend函数
    GlobalScope.launch {
        delay(500)
        println("world")
    }
    println("hello,")
    sleep(1000)
}

fun testCoroutine() {

}

/**
 * 除了直接返回结果的情况，挂起函数一定会以resume结尾，要么返回result，要么返回异常。代表这个挂起函数返回了。
 * 调用resume意义在于重新回调BaseContinuationImpl的resumeWith，进而唤醒状态机，继续执行协程体的代码。
 *
 * 我们自定义的suspend函数，一定要利用suspendCoroutine 获得续体，即状态机对象，否则无法实现真正的挂起与resume
 *
 */
suspend fun hello() = suspendCoroutine<Int> {
    thread {
        Thread.sleep(200)
        it.resume(100)
    }
}

class MyContinuation() : Continuation<Int> {
    override val context: CoroutineContext
        get() = CoroutineName("co-01")

    override fun resumeWith(result: Result<Int>) {
        print( "MyContinuation resumeWith 结果 = ${result.getOrNull()} ${Thread.currentThread().name}")
    }
}
