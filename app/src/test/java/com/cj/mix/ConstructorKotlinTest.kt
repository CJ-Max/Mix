package com.cj.mix

import com.cj.mix.util.User1
import com.cj.mix.util.User2
import com.google.gson.Gson
import org.junit.Test

class ConstructorKotlinTest {
    @Test
    fun testConstructor() {
        println("cjjjjj1 " + Gson().fromJson("{}", User1::class.java).name)
        println("cjjjjj2 " + Gson().fromJson("{}", User2::class.java).name)
    }
}