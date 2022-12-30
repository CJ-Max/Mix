package com.cj.mix.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


fun main(args: Array<String>) {

}

class TypeTokenTest {
    // TypeToken 获取泛型的类型信息
    public class Response<T>(var code: Int, var msg: String, var body: T)
    data class Person(var id: String, var name: String, var age: Int)
    public class Server {
        companion object {
            public fun getPersonStr(): String {
                var person = Person("123", "cj", 13)
                var resp = Response<Person>(1, "", person)
                return Gson().toJson(resp)
            }


            public fun getPersonObj(json: String) {
                var respModel = Gson().fromJson<Response<Person>>(
                    getPersonStr(),
                    object : TypeToken<Response<Person>>() {}.type
                )
            }
        }
    }
}