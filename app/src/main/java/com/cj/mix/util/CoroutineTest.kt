package com.cj.mix.util

import android.util.Log
import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.coroutines.*
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

/**
 * Created by SpiderG on 4/16/22.
 * About :
 */
class CoroutineTest {
}

suspend fun doMeasureOne(): Int {
    delay(1000)
    return 5
}
suspend fun doMeasureTwo(): Int {
    delay(100)
    return 6
}
suspend fun main() = coroutineScope{
    val time = measureTimeMillis {
        val one = async { doMeasureOne() }
        val two = async {  doMeasureTwo() }
        println("${one.await() + two.await()}")
    }
    println("total time :$time")
}
//fun main() {
//    var array = intArrayOf(1,2,3)
//    println("start ${Thread.currentThread().name}")
//    var batx = suspend {
//        val a = hello()
//        a
//    }
//    batx.createCoroutine(MyContinuation())
//    println("end")
//    // 是一个suspend函数
//    GlobalScope.launch {
//        println("launch: " + Thread.currentThread().name)
//        delay(500)
//        println("world")
//    }
//    println("hello,")
//    println("main: " + Thread.currentThread().name)
//    runBlocking {
//        delay(1000)
//    }
//}

//fun main() = runBlocking {
//    launch {
//        delay(500)
//        println("world: ${Thread.currentThread().name}")
//    }
//    println("hello: ${Thread.currentThread().name}")
//}
//
//fun main() = runBlocking { // this: CoroutineScope
//    coroutineScope {  // coroutineScope 挂起函数，会block后面所有的进度
//        println("start1111 Task from coroutine scope")
//        delay(200L)
//        println("end1111 Task from coroutine scope")
//    }
//    launch {
//        println("start Task from runBlocking")
//        delay(200L)
//        println("Task from runBlocking")
//    }
//
//    coroutineScope { // 创建一个协程作用域
//        println("start Task from coroutine scope")
//        launch { // 构建器，非挂起函数
//            println("start Task from nested launch")
//            delay(500L)
//            println("Task from nested launch")
//        }
//
//        delay(100L)
//        println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
//    }
//
//    println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
//}

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
        print("MyContinuation resumeWith 结果 = ${result.getOrNull()} ${Thread.currentThread().name}")
    }
}
