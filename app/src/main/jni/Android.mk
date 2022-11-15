LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := signal_lib

LOCAL_SRC_FILES := com_cj_mix_SignalCatch.c

include $(BUILD_SHARED_LIBRARY)