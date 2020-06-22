package com.hadymic.sqlgenerator.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseResult {
    private int code;
    private String msg;
    private Object data;

    public static BaseResult success(Object data) {
        return new BaseResult(0, "success", data);
    }

    public static BaseResult fail(Object data) {
        return new BaseResult(0, "fail", data);
    }
}
