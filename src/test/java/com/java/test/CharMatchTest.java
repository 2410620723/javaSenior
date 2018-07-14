package com.java.test;

import com.google.common.base.CharMatcher;

/**
 * 去掉字符串中的空格，包括全角、半角空格
 * Created by zxm on 2018/7/14.
 */
public class CharMatchTest {
    public static void main(String[] args) {
        String str = "　中华人 民共和国　　 ";
        System.out.println(CharMatcher.whitespace().trimFrom(str));
    }
}
