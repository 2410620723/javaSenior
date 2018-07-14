package com.java.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 过滤符合条件的List集合
 * Created by zxm on 2018/7/14.
 */
public class FilterList {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        List<Map<String, Object>> list = prepareData();
        List<Map<String, Object>> resultData;
        String name = "name1";
        /*resultData = ListUtils.filter(list, new ListUtilsHook<Map<String, Object>>() {
            @Override
            public boolean compare(Map<String, Object> stringObjectMap) {
                return name.equals(stringObjectMap.get("name"));
            }
        });*/
        resultData = list.parallelStream().filter(m -> name.equals(m.get("name"))).collect(Collectors.toList());
        /*for (Map<String, Object> m : list) {
            if (name.equals(m.get("name"))) {
                resultData.add(m);
            }
        }*/
        long endTime = System.currentTimeMillis();
        System.out.println(resultData);
        System.out.println("时间长度:" + (endTime - startTime));
    }
    private static List<Map<String, Object>> prepareData() {
        List<Map<String,Object>> data = new ArrayList<>();
        Map<String, Object> map;
        for (int i = 1; i <= 1000000; i++) {
            map = new HashMap<>();
            map.put("name", "name" + i);
            map.put("age", i);
            data.add(map);
        }
        map = new HashMap<>();
        map.put("name", "name1");
        map.put("age", "21");
        data.add(map);
        return data;
    }
}
