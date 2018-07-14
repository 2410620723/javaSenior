package com.java.test.util;

import com.java.test.hook.ListUtilsHook;

import java.util.ArrayList;
import java.util.List;

/**
 * 比较两个对象是否相等
 * Created by zxm on 2018/7/14.
 */
public class ListUtils {
    public static <T>List<T> filter(List<T> list, ListUtilsHook<T> hook){
        ArrayList<T> result = new ArrayList<>();
        for (T t : list) {
            if (hook.compare(t)) {
                result.add(t);
            }
        }
        result.trimToSize();
        return result;
    }
}
