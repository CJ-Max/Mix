package com.cj.mix;

/**
 * Created by Oliver on 2022/8/16.
 */
class SignalCatch {
    static {
        System.loadLibrary("signal_lib");
    }
    public native void init(SignalCB signalCB);
    public static native String sayHello();

    public interface SignalCB{
        void signal(int sigValue);
    }
}
