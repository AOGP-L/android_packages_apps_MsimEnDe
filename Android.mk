LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_ASSET_DIR := $(LOCAL_PATH)/assets
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res
LOCAL_SRC_FILES := \
    $(call all-java-files-under,java) \
    
LOCAL_AAPT_INCLUDE_ALL_RESOURCES := true

LOCAL_AAPT_FLAGS := \
    --auto-add-overlay \
    
LOCAL_PACKAGE_NAME := MsimEnDe

include $(BUILD_PACKAGE)

include $(call all-makefiles-under,$(LOCAL_PATH))
