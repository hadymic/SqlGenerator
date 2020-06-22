package com.hadymic.sqlgenerator;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.*;

public class DataAnalysis {

    private Map<String, int[]> keyMap = new TreeMap<>();
    private Map<Set<String>, Integer> keySetMap = new TreeMap<>(Comparator.comparingInt(Set::size));

    public void read2Buffer(String filePath) {
        File file = new File(filePath);
        File newFile = new File(filePath + "format.log");
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(newFile))) {
            String line;
            int lineNum = 1;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("\\\\+\"", "\"");//剔除例如\\"，这样的转义
                line = line.replaceAll("\\\\\\\\/", "/");//更改\\/为/
                line = line.replaceAll("\"\\{", "{");//大括号前有引号就去除
                line = line.replaceAll("\\}\"", "}");//大括号后有引号就去除
                //去除多余的[]
                line = line.replaceAll("\\[\\[\\{", "[{");
                line = line.replaceAll("\\}\\]\\]", "}]");
                line = line.replaceAll("\\}\\],\\[\\{", "},{");

                Set<String> keySet = new TreeSet<>();
                System.out.println(line);
                //将json单层化并转化成map
                parseJson(keySet, line);
                if (compareSet(keySet)) {

                    keySetMap.put(keySet, lineNum);
                }

                //输出格式化完成的json数据
                writer.write(line + "\n");
                writer.flush();
                lineNum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void parseJson(Set<String> set, String json) {
        Map map = JSONObject.parseObject(json, Map.class);
        parseJson(set, map, "");
    }

    public void parseJson(Set<String> set, Object value, String parentKey) {
        if (value instanceof Map) {
            Map<String, Object> map = (Map) value;
            for (String key : map.keySet()) {
                parseJson(set, map.get(key), parentKey + "." + key);
            }
        } else if (value instanceof List) {
            List list = (List) value;
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                parseJson(set, iterator.next(), parentKey + "-0");
            }
        } else if (value == null || "".equals(value)) {
            int[] arr = keyMap.getOrDefault(parentKey, new int[2]);
            arr[0] += 1;//出现的次数
            arr[1] += 1;//为空出现的次数
            keyMap.put(parentKey, arr);
            set.add(parentKey);
        } else {
            int[] arr = keyMap.getOrDefault(parentKey, new int[2]);
            arr[0] += 1;
            keyMap.put(parentKey, arr);
            set.add(parentKey);
        }
    }

    //比较是否已经含有该set，有了返回false，没有返回true
    public boolean compareSet(Set<String> set) {
        for (Set<String> keySet : keySetMap.keySet()) {
            if (keySet.equals(set)) {
                return false;
            }
        }
        return true;
    }


    @Test
    public void test() {
        String filePath = "D:\\log-20200617.log";
        read2Buffer(filePath);

        System.out.println(keyMap.keySet());

        for (String key : keyMap.keySet()) {
            System.out.println(key + ":" + Arrays.toString(keyMap.get(key)));
        }

        System.out.println(keySetMap);
    }
}

