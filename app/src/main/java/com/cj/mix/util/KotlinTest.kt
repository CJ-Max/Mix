package com.cj.mix.util

import android.content.SharedPreferences
import kotlin.properties.Delegates

class KotlinTest {
    lateinit var name: String
    fun print() {
        print(name)

    }

    // 委托 age$delegate 字节码中生成一个附加属性
    var age by Delegates.observable(18) { property, oldValue, newValue ->
        println("回调 $property $oldValue $newValue")
    }

    fun printAge() {
        println("$age")
        age = 25
        println("$age")
    }

    // 只能是val型
    val money: Int by lazy {
        1000
    }
}

fun <T> T.myApply(block: T.() -> Unit):T {
    block()
    return this
}


fun main() {
    var test: KotlinTest =  KotlinTest()
    test.myApply {
        test.age = 3555
    }
    var strb = test.run {
        StringBuffer()
    }
    strb.append()

    test.printAge()
}