package com.hadymic.sqlgenerator.utils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 对象工具类
 */
public class ObjectUtils {
    /**
     * 判断对象的所有属性是否都是null，如果所有都是null返回true，如果有一个不是，则返回false
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static boolean checkObjAllFieldIsNull(Object obj) {
        try {
            for (Field f : obj.getClass().getDeclaredFields()) {
                f.setAccessible(true);
                if (f.get(obj) != null) {
                    if (f.get(obj) instanceof List) {
                        List list = (List) f.get(obj);
                        if (list.size() > 0) {
                            list.forEach(ObjectUtils::checkObjAllFieldIsNull);
                        }
                    }
                    if (f.get(obj) instanceof Number || f.get(obj) instanceof Character || f.get(obj) instanceof Boolean) {
                        return false;
                    }
                    if (f.get(obj) instanceof String && "".equals(f.get(obj))) {
                        continue;
                    }
                    return checkObjAllFieldIsNull(f.get(obj));
                }
            }
            return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }
}
