//
// Created by lls on 2022/8/16.
//

#ifndef MIX_JNILOG_H
#define MIX_JNILOG_H

#include<android/log.h>
#define MY_LOG_TAG "signal_tag"

#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, MY_LOG_TAG, __VA_ARGS__)

#endif //MIX_JNILOG_H
