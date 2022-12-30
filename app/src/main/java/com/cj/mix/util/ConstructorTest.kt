package com.cj.mix.util

import org.jetbrains.annotations.TestOnly

class ConstructorTest {
}
// https://cloud.tencent.com/developer/article/1731220?from=article.detail.1672483
class User1(var id: String, var name: String = "123") {
    init {
        print("user1")
    }
}
// 反编译字节码会发现会创建一个无参构造函数
class User2(var id: String = "id", var name: String = "456") {
    init {
        print("user2")
    }
}

// class User3(): User1() {}
// 类默认是 final 无法继承的，需要改为 open 才能继承
// class User4() : User2() { }