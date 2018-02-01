package com.java.reflect;

import java.lang.reflect.Constructor;

public class ReflectDemo {
    public static void main(String[] args) throws Exception {
        String string = "动脑学院";
        // 1. 实例化对象，getClass()
        Class c1 = string.getClass();

        // 2. 类.class
        Class c2 = String.class;

        // 3. Class.forName("")
        Class c3 = Class.forName("java.lang.String");

        // 这三个字节模板是否相等
        System.out.println(c1 == c2);// true
        System.out.println(c1 == c3);// true
        System.out.println(int.class == Integer.class);// false
        System.out.println(Integer.TYPE == int.class);// true
        // byte short int long float double char boolean void
        // 是我们九大预定义的基础类型
        System.out.println(c1.isPrimitive());// false
        // 获取实例的构造方法
        Constructor constructor3 = c3.getConstructor(StringBuffer.class);
        String s3 = (String) constructor3.newInstance(new StringBuffer("反射"));
        System.out.println(s3.charAt(1));
    }
}
