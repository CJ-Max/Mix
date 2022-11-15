package com.cj.mix.test;

import com.alibaba.android.arouter.launcher.ARouter;

public class JavaTest {
    public static void main(String[] args) {
        ARouter.getInstance()
    }

    // https://www.kotlincn.net/docs/reference/generics.html
    void testGeneric() {
//        List<String> strs = new ArrayList<String>();
//        List<Object> objs = strs; // ！！！此处的编译器错误让我们避免了之后的运行时异常
//        objs.add(1); // 这里我们把一个整数放入一个字符串列表
//        String s = strs.get(0);
    }
}
