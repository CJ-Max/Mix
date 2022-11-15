package com.cj.mix.test

class TestInline {
    fun runCatch(block: (String?) -> Unit) {
        block.invoke("12345")
    }

    fun test() {
        runCatch {
            println(it)
        }
    }
}
