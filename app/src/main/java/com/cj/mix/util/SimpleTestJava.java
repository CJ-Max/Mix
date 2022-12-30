package com.cj.mix.util;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class SimpleTestJava {
    class TestLateinit {
        String name;

        void print() {
            System.out.println(name);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        String str = "213";
        Arrays.sort(str.toCharArray());
        List<List<String>> lists = new ArrayList();
        lists.add(new ArrayList<String>() {{
            add("!2");
        }});

        List<String> list = new ArrayList<String>();
        list.toArray();
        // 插入列表头
        lists.add(0, list);
        lists.set(0, list);

        Map<Character, Integer> targetMap = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : targetMap.entrySet()) {

        }
        Map<Integer, Integer> map = new HashMap<>();

        Collections.sort(list, (a, b) -> a.length() - b.length());
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>((a, b) -> map.get(a) - map.get(b));
        int removeData = queue.remove();

        int[][] directions = new int[][]{{0, 0}, {1, 1}, {2, 2}, {3, 3}};
    }

    private void testJvmOverloads() {
        JvmOverLoadsTest jvmOverLoadsTest = new JvmOverLoadsTest("test");
        JvmOverLoadsTest jvmOverLoadsTest1 = new JvmOverLoadsTest("test", 2);
        JvmOverLoadsTest jvmOverLoadsTest2 = new JvmOverLoadsTest("test", 3, false);
    }
}
