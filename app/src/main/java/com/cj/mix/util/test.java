package com.cj.mix.util;

public class test {
    public interface CusLamadba {
        public void checkRun(int x);
    }
    public static void main() {
//        ArrayList<Character> list = new ArrayList<>();
//        String s = "10";
        CusLamadba objjj = (x) -> {
            System.out.println(x);
        };
        objjj.checkRun(2);
    }
}
