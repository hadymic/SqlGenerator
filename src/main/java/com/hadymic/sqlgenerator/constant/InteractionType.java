package com.hadymic.sqlgenerator.constant;

/**
 * 广告类型常量
 *
 * @author Hadymic
 */
public enum InteractionType {
    //浏览器
    INTERACTION_TYPE_BROWSER(2, "browser"),
    //电话
    INTERACTION_TYPE_DIAL(5, "dial"),
    //下载
    INTERACTION_TYPE_DOWNLOAD(4, "download"),
    //落地页
    INTERACTION_TYPE_LANDING_PAGE(3, "landingpage"),
    //未知
    INTERACTION_TYPE_UNKNOWN(-1, "none");

    private int type;
    private String relativePath;

    InteractionType(int type, String relativePath) {
        this.type = type;
        this.relativePath = relativePath;
    }

    public static String getPath(int type) {
        for (InteractionType interactionType : InteractionType.values()) {
            if (type == interactionType.getType()) {
                return interactionType.getRelativePath();
            }
        }
        return INTERACTION_TYPE_UNKNOWN.getRelativePath();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }
}
