package com.cj.mix.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ShellExecutor {
    public static void execute(String... cmd) {
        try {
            Process process = Runtime.getRuntime().exec("cat /proc/loadavg");
            process.waitFor();
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder("result=");
            String line;
            while ((line = in.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            android.util.Log.e("ProcessCpuTracker", stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


