//
// Created by lls on 2022/8/16.
//

#include <jni.h>
#include "com_cj_mix_SignalCatch.h"

JNIEXPORT jstring JNICALL Java_com_cj_mix_SignalCatch_sayHello
  (JNIEnv *env, jobject obj){
  return (*env)->NewStringUTF(env, "test, hello signal");
 }
