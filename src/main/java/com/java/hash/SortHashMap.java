package com.java.hash;

import java.util.*;

/**
 * 已知一个HashMap<Integer, User>集合，User有name(String)，age(Integer)属性，请写一个方法实现对HashMap的排序功能
 * 该方法接收HashMap<Integer, User> 为形参，返回值类型为HashMap<Integer, User>
 * 要求对HashMap中的User的age倒序排序。排序时key、value不得拆散。
 * Created by zxm on 2018/5/27.
 */
public class SortHashMap {
    public static void main(String[] args) {
        Map<Integer, User> userMap = new HashMap<>();
        userMap.put(1, new User("张三", 21));
        userMap.put(2, new User("李四", 24));
        userMap.put(3, new User("王五", 23));
        HashMap<Integer, User> sortedUser = sort(userMap);
        System.out.println(sortedUser);
    }

    /**
     * 根据User的age属性降序排序
     *
     * @param userMap
     * @return
     */
    private static HashMap<Integer, User> sort(Map<Integer, User> userMap) {
        Set<Map.Entry<Integer, User>> userSet = userMap.entrySet();
        List<Map.Entry<Integer, User>> userList = new ArrayList<>(userSet);
        // 使用集合的排序类给list排序
        Collections.sort(userList, new Comparator<Map.Entry<Integer, User>>() {
            @Override
            public int compare(Map.Entry<Integer, User> o1, Map.Entry<Integer, User> o2) {
                return o2.getValue().getAge() - o1.getValue().getAge();
            }
        });
        LinkedHashMap<Integer, User> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, User> user : userList) {
            linkedHashMap.put(user.getKey(), user.getValue());
        }
        return linkedHashMap;
    }
}
