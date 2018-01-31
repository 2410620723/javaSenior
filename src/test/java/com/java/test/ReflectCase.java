package com.java.test;

import java.lang.reflect.Method;

public class ReflectCase {
    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        try {
            Method method = Proxy.class.getDeclaredMethod("run");
            method.invoke(proxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class Proxy {
        void run() {
            System.out.println("run...");
        }
    }
}
