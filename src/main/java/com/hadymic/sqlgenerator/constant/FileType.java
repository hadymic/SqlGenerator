package com.hadymic.sqlgenerator.constant;

/**
 * 文件类型常量
 *
 * @author Hadymic
 */
public enum FileType {
    //APK
    FILE_TYPE_APK("apk"),
    //ICON
    FILE_TYPE_ICON("icon"),
    //IMAGE
    FILE_TYPE_IMAGE("image"),
    //VIDEO
    FILE_TYPE_VIDEO("video");

    private String path;

    FileType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
